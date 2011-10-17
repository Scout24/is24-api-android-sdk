package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;
import de.is24.restapi.android.sdk.http.HttpHeader;
import de.is24.restapi.android.sdk.http.response.Response;


/**
 * Interface for marking a {@link ResponseHandler} withing the is24 context
 * @author Hasan Hosgel
 *
 * @param <T>
 */
public interface IS24ResponseHandler<T> {
  /**
   * return the flag if the request has an accept encoding header
   * @return
   */
  boolean hasAcceptEncodingHeader();

  /**
   * offers the accept header
   * @return
   */
  HttpHeader getAcceptHeader();

  /**
   * return true, if the {@link HttpRequestExecuter} can consume the entity automatically.
   */
  boolean isAutoEntityConsume();

  /**
   * handling of the response
   * @throws IOException
   */
  Response<T> handleResponse(InputStream response, int responseCode, Response<T> result) throws IOException;
}
