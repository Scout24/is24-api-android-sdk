package de.is24.restapi.android.sdk.http.response.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;


/**
 * An abstract {@link IS24ResponseHandler} for parsing Strings
 * @author Hasan Hosgel
 *
 * @param <T>
 */
public abstract class StringResponseHandler<T> extends BaseResponseHandler<T> {
  protected String dumpResponse(InputStream response) throws IOException {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(response), 8192);

      String line = null;

      StringBuilder result = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }

      Log.d(getTag(), "response content: " + result);
      return result.toString();
    } finally {
      if (null != reader) {
        reader.close();
      }
    }
  }

  public boolean isAutoEntityConsume() {
    return true;
  }

}
