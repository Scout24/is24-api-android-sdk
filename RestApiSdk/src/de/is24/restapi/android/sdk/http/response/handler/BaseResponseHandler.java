package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import de.is24.restapi.android.sdk.http.HttpParameters;


/**
 * An abstract {@link IS24ResponseHandler} for handling the response and putting it into an {@link InputStream}
 * and if needed into a {@link GZIPInputStream}
 * @author Hasan Hosgel
 *
 * @param <T>
 */
abstract class BaseResponseHandler<T> implements IS24ResponseHandler<T> {
  protected abstract String getTag();

  /**
   * Returns the {@link InputStream} wrapping the HTTP response and wrap it with a {@link GZIPInputStream},
   * if it is a packed response.
   * @param entity
   * @return an {@link InputStream};
   * @throws IOException
   */
  protected InputStream getContent(HttpEntity entity) throws IOException {
    InputStream responseStream = entity.getContent();
    Header header = entity.getContentEncoding();

    if ((responseStream == null) || (header == null)) {
      return responseStream;
    }

    if ((header.getValue() != null) && header.getValue().contains(HttpParameters.GZIP)) {
      return new GZIPInputStream(responseStream, 8192);
    }
    return responseStream;
  }

  /**
   * {@inheritDoc}
   */
  public boolean hasAcceptEncodingHeader() {
    return true;
  }

}
