package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import de.is24.restapi.android.sdk.http.HttpHeader;


/**
 * An abstract {@link IS24ResponseHandler} for handling json requests
 * @author Hasan Hosgel
 *
 * @param <T>
 */
abstract class JsonResponseHandler<T> extends StringResponseHandler<T> {
  /**
     * parse the {@link JSONObject} from the given HTTP response.
     * @param response
     * @return an {@link JSONObject} representing the HTTP response.
     * @throws IOException
     * @throws JSONException
     */
  protected JSONObject parseJson(InputStream response) throws IOException, JSONException {
    JSONObject jsonResponse = new JSONObject(dumpResponse(response));
    return jsonResponse;
  }

  /**
   * {@inheritDoc}
   */
  public HttpHeader getAcceptHeader() {
    return HttpHeader.ACCEPT_JSON;
  }

  protected JSONArray getJSONArrayForObject(Object object) {
    JSONArray resultArray = null;
    if (null != object) {
      if (object instanceof JSONArray) {
        resultArray = (JSONArray) object;
      } else {
        resultArray = new JSONArray();
        resultArray.put(object);
      }
    }
    return resultArray;
  }
}
