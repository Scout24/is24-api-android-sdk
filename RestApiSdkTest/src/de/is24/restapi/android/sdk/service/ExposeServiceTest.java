package de.is24.restapi.android.sdk.service;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;
import de.is24.restapi.android.sdk.domain.Expose;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import de.is24.restapi.android.sdk.http.RequestUrl;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.test.MockRequestExecuter;
import de.is24.restapi.android.sdk.test.NetworkTestHelper;


@SmallTest
public class ExposeServiceTest extends AndroidTestCase {
  private ExposeService service;
  private MockRequestExecuter executer;

  @Override
  protected void setUp() throws Exception {
    LibraryContext.getInstance().initApplicationContext(getContext());
    service = ExposeService.getInstance();
    executer = new MockRequestExecuter();
    NetworkTestHelper.setHttpExecuter(executer);
  }

  public void testGetExposeWithParsingError() throws Exception {
    Response<Expose> expose = new Response<Expose>();
    expose.statusCode = ServiceException.PARSING_ERROR;
    executer.results.add(expose);
    try {
      service.getExpose("uuid");
      fail();
    } catch (ServiceException e) {
      assertEquals(ServiceException.PARSING_ERROR, e.errorCode);
    }

    RequestUrl request = executer.urlRequests.pop();
    assertEquals(RequestUrl.GET, request.method);
    assertEquals(LibraryContext.getInstance().getApplicationContext().getString(R.string.url_search_service_dev) +
      "expose/uuid", request.uri);
  }

  public void testGetExposeWithUnknownType() throws Exception {
    Response<Expose> expose = new Response<Expose>();
    expose.statusCode = ServiceException.UNKNOWN_TYPE;
    executer.results.add(expose);
    try {
      service.getExpose("uuid");
      fail();
    } catch (ServiceException e) {
      assertEquals(ServiceException.UNKNOWN_TYPE, e.errorCode);
    }

    RequestUrl request = executer.urlRequests.pop();
    assertEquals(LibraryContext.getInstance().getApplicationContext().getString(R.string.url_search_service_dev) +
      "expose/uuid", request.uri);
  }

  public void testGetExposeWithUnknownScoutId() throws Exception {
    Response<Expose> expose = new Response<Expose>();
    executer.results.add(expose);
    try {
      service.getExpose("uuid");
      fail();
    } catch (ServiceException e) {
      assertEquals(ServiceException.NOT_FOUND, e.errorCode);
    }

    RequestUrl request = executer.urlRequests.pop();
    assertEquals(LibraryContext.getInstance().getApplicationContext().getString(R.string.url_search_service_dev) +
      "expose/uuid", request.uri);
  }

  public void testGetExposeSuccess() throws Exception {
    Response<Expose> response = new Response<Expose>();
    response.result = new Expose(RealEstateType.HouseBuy);
    response.success = true;
    executer.results.add(response);

    MiniExpose expose = service.getExpose("uuid");
    assertNotNull(expose);

    RequestUrl request = executer.urlRequests.pop();
    assertEquals(LibraryContext.getInstance().getApplicationContext().getString(R.string.url_search_service_dev) +
      "expose/uuid", request.uri);
  }
}
