package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.test.NetworkTestHelper;


public class JsonResponseHandlerTest extends AndroidTestCase {
  private Json handler;

  @Override
  protected void setUp() throws Exception {
    handler = new Json();
  }

  public void testError() throws Exception {
    Response<JSONObject> response = handler.handleResponse(NetworkTestHelper.createResponseInputStream(""),
      HttpStatus.SC_OK,
      new Response<JSONObject>());
    assertNotNull(response);
    assertNull(response.result);
    assertFalse(response.success);
  }

  public void testSuccess() throws Exception {
    Response<JSONObject> response = handler.handleResponse(NetworkTestHelper.createResponseInputStream(
      "{\"success\":\"true\"}"), HttpStatus.SC_OK,
      new Response<JSONObject>());
    assertNotNull(response);
    assertTrue(response.success);
    assertTrue(response.result.getBoolean("success"));
  }

  public void testGetJsonArrayForObject() throws Exception {
    JSONObject jsonObject = new JSONObject("{\"test\":{\"success\":\"true\"}}");
    JSONArray response = handler.getJSONArrayForObject(jsonObject);
    assertNotNull(response);
    assertTrue(response instanceof JSONArray);
    assertEquals("[{\"test\":{\"success\":\"true\"}}]", response.toString());

    JSONArray object = new JSONArray("[{\"test\":{\"success\":\"true\"}}]");
    response = handler.getJSONArrayForObject(object);
    assertNotNull(response);
    assertTrue(response instanceof JSONArray);
    assertEquals("[{\"test\":{\"success\":\"true\"}}]", response.toString());
  }

  private class Json extends JsonResponseHandler<JSONObject> {
    @Override
    protected String getTag() {
      return "JsonResponseHandlerTest";
    }

    @Override
    public Response<JSONObject> handleResponse(InputStream response,
      int responseCode, Response<JSONObject> result) throws IOException {
      try {
        result.result = parseJson(response);
        result.success = true;
        return result;
      } catch (JSONException e) {
      }
      return result;
    }
  }

}
