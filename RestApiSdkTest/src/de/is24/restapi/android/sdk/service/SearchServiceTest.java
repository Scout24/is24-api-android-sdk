package de.is24.restapi.android.sdk.service;

import java.util.ArrayList;
import android.location.Location;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;
import de.is24.restapi.android.sdk.domain.CommonCriteria;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.Page;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import de.is24.restapi.android.sdk.domain.SearchConstants;
import de.is24.restapi.android.sdk.domain.SearchQuery;
import de.is24.restapi.android.sdk.domain.Sorting;
import de.is24.restapi.android.sdk.http.RequestUrl;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.test.MockRequestExecuter;
import de.is24.restapi.android.sdk.test.NetworkTestHelper;
import de.is24.restapi.android.sdk.util.StringUtils;


@SmallTest
public class SearchServiceTest extends AndroidTestCase {
  private SearchService service;
  private MockRequestExecuter executer;

  protected void setUp() throws Exception {
    LibraryContext.getInstance().initApplicationContext(getContext());
    service = SearchService.getInstance();
    executer = new MockRequestExecuter();
    NetworkTestHelper.setHttpExecuter(executer);
  }

  public void testExecute() throws Exception {
    Response<Page> response = new Response<Page>();
    response.result = new Page();
    response.result.results = new ArrayList<MiniExpose>();
    response.success = true;
    executer.results.add(response);

    SearchQuery query = new SearchQuery(RealEstateType.ApartmentBuy);
    query.setSearchType(SearchQuery.SEARCH_TYPE_INTERVAL);
    query.put(CommonCriteria.GEOCODE_ID, "111");
    query.sorting = Sorting.ROOMS_HIGH;

    Page page = service.execute(query, 2, false);
    assertNotNull(page);
    assertNotNull(page.results);
    assertEquals(0, page.results.size());

    RequestUrl request = executer.urlRequests.pop();
    assertEquals(LibraryContext.getInstance().getApplicationContext().getString(R.string.url_search_service_dev) +
      "search/region?realestatetype=" + RealEstateType.ApartmentBuy + "&geocodes=111&pagenumber=2&sorting=" +
      StringUtils.C_MINUS + Sorting.ROOMS_HIGH.restapiName, request.uri);
  }

  public void testExecuteWithParsingError() throws Exception {
    Response<Page> response = new Response<Page>();
    response.statusCode = ServiceException.PARSING_ERROR;
    executer.results.add(response);

    Location loc = new Location("");
    loc.setLatitude(10.1d);
    loc.setLongitude(56.0d);

    SearchQuery query = new SearchQuery(RealEstateType.ApartmentRent);
    query.setSearchType(SearchQuery.SEARCH_TYPE_GPS);
    query.put(CommonCriteria.LOCATION, loc);
    try {
      service.execute(query, 0, true);
      fail();
    } catch (ServiceException e) {
      assertEquals(ServiceException.PARSING_ERROR, e.errorCode);
    }

    RequestUrl request = executer.urlRequests.pop();
    assertEquals(RequestUrl.GET, request.method);
    assertEquals(LibraryContext.getInstance().getApplicationContext().getString(R.string.url_search_service_dev) +
      "search/radius?realestatetype=" + RealEstateType.ApartmentRent + "&geocoordinates=10.1000;56.0000;" +
      SearchConstants.DEFAULT_RADIUS, request.uri);
  }

  public void testExecuteWithMissingLocation() throws Exception {
    Response<Page> response = new Response<Page>();
    response.result = new Page();
    response.result.results = new ArrayList<MiniExpose>();
    response.success = true;
    executer.results.add(response);

    SearchQuery query = new SearchQuery(RealEstateType.LivingBuySite);
    query.setSearchType(SearchQuery.SEARCH_TYPE_ADDRESS);
    try {
      service.execute(query, 0, false);
      fail();
    } catch (ServiceException e) {
      assertEquals(ServiceException.PARSING_ERROR, e.errorCode);
      assertEquals("missing location in query", e.getMessage());
    }
  }

}
