package de.is24.restapi.android.sdk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import android.location.Location;
import android.text.TextUtils;
import android.util.Log;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;
import de.is24.restapi.android.sdk.domain.CommonCriteria;
import de.is24.restapi.android.sdk.domain.CriteriaEnum;
import de.is24.restapi.android.sdk.domain.Page;
import de.is24.restapi.android.sdk.domain.Range;
import de.is24.restapi.android.sdk.domain.RestApiAvailable;
import de.is24.restapi.android.sdk.domain.SearchConstants;
import de.is24.restapi.android.sdk.domain.SearchQuery;
import de.is24.restapi.android.sdk.http.HttpRequestExecuter;
import de.is24.restapi.android.sdk.http.NoConnectionException;
import de.is24.restapi.android.sdk.http.RequestUrl;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.http.response.handler.IS24ResponseHandler;
import de.is24.restapi.android.sdk.http.response.handler.SearchResponseHandler;
import de.is24.restapi.android.sdk.util.Formatter;
import de.is24.restapi.android.sdk.util.StringUtils;


/**
 * This service is used for getting the result page filtered by the search query from the the old xmlrpcapi
 * @author Hasan Hosgel
 *
 */
public class SearchService {
  private static final String TAG = "ApiSearchServiceImpl";
  private SearchResponseHandler searchHandler;

  private static SearchService INSTANCE;

  public static SearchService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new SearchService();
    }
    return INSTANCE;
  }

  private SearchService() {
  }

  /**
   * This method executes the search query towards the old xmlrpcapi and returns the result page
   * @param request
   * @return
   * @throws ServiceException
   * @throws NoConnectionException
   * @throws SessionInvalidException
   */
  public Page execute(final SearchQuery query, final int pageNumber, boolean strictMode) throws ServiceException,
    NoConnectionException {
    StringBuilder url = new StringBuilder();
    if (HttpRequestExecuter.getInstance().isLiveUrl()) {
      url.append(LibraryContext.getInstance().getApplicationContext().getResources().getString(
          R.string.url_search_service_live));
    } else {
      url.append(LibraryContext.getInstance().getApplicationContext().getResources().getString(
          R.string.url_search_service_dev));
    }
    if (SearchQuery.SEARCH_TYPE_INTERVAL == query.getSearchType()) {
      url.append("search/region?realestatetype=");
      url.append(query.realEstateType);
      url.append("&geocodes=");
      url.append(query.getString(CommonCriteria.GEOCODE_ID));
    } else {
      url.append("search/radius?realestatetype=");
      url.append(query.realEstateType);
      url.append("&geocoordinates=");

      final Location loc = query.opt(CommonCriteria.LOCATION, (Location) null);
      if (null != loc) {
        url.append(Formatter.coordinateFormatter.format(loc.getLatitude()));
        url.append(StringUtils.C_SEMICOLON);
        url.append(Formatter.coordinateFormatter.format(loc.getLongitude()));
        url.append(StringUtils.C_SEMICOLON);
      } else {
        throw new ServiceException(ServiceException.PARSING_ERROR, "missing location in query");
      }

      final String radius = query.getString(CommonCriteria.RADIUS);
      if (!TextUtils.isEmpty(radius) && TextUtils.isDigitsOnly(radius)) {
        url.append(radius);
      } else {
        url.append(SearchConstants.DEFAULT_RADIUS);
      }
    }
    addFilterCriteria(url, query, pageNumber);

    Response<Page> response = HttpRequestExecuter.getInstance().execute(new RequestUrl(url.toString(), RequestUrl.GET,
        RequestUrl.OAUTH_2_LEGGED), getSearchHandler(strictMode));

    if (response.success) {
      return response.result;
    } else {
      Log.w(TAG, "cannot search parse response" + response.optionalMessage);
    }
    throw new ServiceException(ServiceException.PARSING_ERROR, (response == null) ? null : response.optionalMessage);
  }

  private void addFilterCriteria(final StringBuilder result, final SearchQuery query, final int pageNumber) {
    final HashMap<String, StringBuilder> criterias = new HashMap<String, StringBuilder>();
    String param;
    StringBuilder values;
    for (CriteriaEnum item : query.keySet()) {
      param = item.getRequestParameterName();
      if (null != param) {
        values = criterias.get(param);
        if (null == values) {
          values = getValue(query, item, new StringBuilder());
          criterias.put(param, values);
        } else {
          getValue(query, item, values.append(StringUtils.C_COMMA));
        }
      }
    }
    if (!criterias.isEmpty()) {
      for (String key : criterias.keySet()) {
        result.append(StringUtils.C_AMP).append(key).append(StringUtils.C_EQUAL).append(criterias.get(key));
      }
    }

    if (pageNumber > 0) {
      result.append("&pagenumber=");
      result.append(pageNumber);
    }

    if (query.sorting != null) {
      result.append("&sorting=");
      if (query.sorting.isDescendingOrder) {
        result.append(StringUtils.C_MINUS);
      }
      result.append(query.sorting.restapiName);
    }
  }

  private StringBuilder getValue(SearchQuery query, CriteriaEnum item, StringBuilder builder) {
    Class<?> type = item.getValueType();
    if (Void.class == type) {
      builder.append(item.getRestapiName());
    } else if (Range.class == type) {
      addRange(builder, query.get(item, Range.class), item.getRequestParameterName());
    } else {
      throw new IllegalArgumentException("cannot parse unknown type:" + type);
    }

    return builder;
  }

  void addRange(final StringBuilder result, final Range range, final String parameterName) {
    if (range != null) {
      if (!TextUtils.isEmpty(range.to)) {
        if (TextUtils.isEmpty(range.from)) {
          result.append(StringUtils.C_ZERO).append(StringUtils.C_MINUS).append(range.to.replace(",", "."));
        } else {
          result.append(range.from.replace(",", ".")).append(StringUtils.C_MINUS).append(range.to.replace(",", "."));
        }
      } else if (!TextUtils.isEmpty(range.from)) {
        result.append(range.from.replace(",", "."));
      }

    }
  }

  <T extends RestApiAvailable> void addEnumerations(final StringBuilder result, final ArrayList<T> list,
    final String parametername) {
    if (!list.isEmpty()) {
      result.append(StringUtils.C_AMP).append(parametername);

      Iterator<T> it = list.iterator();
      while (it.hasNext()) {
        RestApiAvailable listItem = it.next();
        result.append(listItem.getRestApiSearchRequestName());
        if (it.hasNext()) {
          result.append(StringUtils.C_COMMA);
        }
      }
    }
  }


  IS24ResponseHandler<Page> getSearchHandler(boolean strictMode) {
    if (searchHandler == null) {
      searchHandler = new SearchResponseHandler();
    }
    searchHandler.setStrictMode(strictMode);
    return searchHandler;
  }
}
