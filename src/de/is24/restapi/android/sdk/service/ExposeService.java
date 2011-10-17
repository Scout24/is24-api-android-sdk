package de.is24.restapi.android.sdk.service;

import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;
import de.is24.restapi.android.sdk.domain.Expose;
import de.is24.restapi.android.sdk.http.HttpRequestExecuter;
import de.is24.restapi.android.sdk.http.NoConnectionException;
import de.is24.restapi.android.sdk.http.RequestUrl;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.http.response.handler.ExposeResponseHandler;
import de.is24.restapi.android.sdk.http.response.handler.IS24ResponseHandler;


/**
 * This Service is used for the getting the expsoe from the restapi
 *
 * @author Hasan Hosgel
 *
 */
public class ExposeService {
  private ExposeResponseHandler exposeHandler;
  private String urlApiRequestService;
  private static ExposeService INSTANCE;

  public static ExposeService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ExposeService();
    }
    return INSTANCE;
  }

  private ExposeService() {
  }

  public Expose getExpose(String uuid) throws ServiceException, NoConnectionException {
    StringBuilder url = new StringBuilder();
    url.append(getRequestUrl());
    url.append("expose/").append(uuid);

    final Response<Expose> response = HttpRequestExecuter.getInstance().execute(
      new RequestUrl(url.toString(), RequestUrl.GET, RequestUrl.OAUTH_2_LEGGED), getExposeHandler());

    if (response.success) {
      return response.result;
    }
    if (response.statusCode == ServiceException.PARSING_ERROR) {
      throw new ServiceException(ServiceException.PARSING_ERROR,
        "cannot parse expose response:" +
        response.optionalMessage);
    }
    if (response.statusCode == ServiceException.UNKNOWN_TYPE) {
      throw new ServiceException(ServiceException.UNKNOWN_TYPE, response.optionalMessage);
    }
    throw new ServiceException(ServiceException.NOT_FOUND, "cannot find Expose:" + uuid);
  }

  private String getRequestUrl() {
    if (null == urlApiRequestService) {
      if (HttpRequestExecuter.getInstance().isLiveUrl()) {
        urlApiRequestService = LibraryContext.getInstance().getApplicationContext().getResources().getString(
          R.string.url_search_service_live);
      } else {
        urlApiRequestService = LibraryContext.getInstance().getApplicationContext().getResources().getString(
          R.string.url_search_service_dev);
      }
    }
    return urlApiRequestService;
  }

  private IS24ResponseHandler<Expose> getExposeHandler() {
    if (exposeHandler == null) {
      exposeHandler = new ExposeResponseHandler();
    }
    return exposeHandler;
  }
}
