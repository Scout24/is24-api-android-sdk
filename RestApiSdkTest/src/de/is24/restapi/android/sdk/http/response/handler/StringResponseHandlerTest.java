/**
 *
 */
package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpStatus;

import android.test.AndroidTestCase;
import de.is24.restapi.android.sdk.http.HttpHeader;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.test.NetworkTestHelper;


public class StringResponseHandlerTest extends AndroidTestCase {
  private StringResponse handler;
  private static final String SIMPLE_JSON_STRING = "{\"success\":\"true\"}";
  private static final String ERROR_RESPONSE = "Hinweis Fehler 404";

  @Override
  protected void setUp() throws Exception {
    handler = new StringResponse();
  }

  public void testSuccess() throws Exception {
    Response<String> response = handler.handleResponse(NetworkTestHelper.createResponseInputStream(
      SIMPLE_JSON_STRING), HttpStatus.SC_OK,
      new Response<String>());
    assertNotNull(response);
    assertNotNull(response.result);
    assertEquals(SIMPLE_JSON_STRING, response.result);
  }

  public void testSuccessWhenResponseCodeIsNotOK() throws Exception {
    Response<String> response = handler.handleResponse(NetworkTestHelper.createResponseInputStream(
      ERROR_RESPONSE), HttpStatus.SC_NOT_FOUND,
      new Response<String>());
    assertNotNull(response);
    assertNotNull(response.result);
    assertEquals(ERROR_RESPONSE, response.result);
  }

  private class StringResponse extends StringResponseHandler<String> {
    @Override
    public HttpHeader getAcceptHeader() {
      return HttpHeader.ACCEPT_JSON;
    }

    @Override
    public Response<String> handleResponse(InputStream response,
      int responseCode, Response<String> result) throws IOException {
      result.result = dumpResponse(response);
      result.success = true;
      return result;
    }

    @Override
    protected String getTag() {
      return "StringResponseHandlerTest";
    }
  }
}
