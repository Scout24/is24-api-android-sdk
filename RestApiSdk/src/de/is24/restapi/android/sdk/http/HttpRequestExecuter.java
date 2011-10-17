package de.is24.restapi.android.sdk.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpStatus;
import android.util.Log;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.http.response.handler.IS24ResponseHandler;


/**
 * Class for executing HTTP requests
 * @author Hasan Hosgel
 *
 */
public class HttpRequestExecuter implements RequestExecuter {
  private static final String TAG = "HttpRequestExecuter";
  private static final NoConnectionException noConnectionException = new NoConnectionException();
  public static RequestExecuter instance;
  private OAuthConsumer consumer2Legged;
  protected boolean isLiveUrl;

  public static RequestExecuter getInstance() {
    if (null == instance) {
      instance = new HttpRequestExecuter();
    }
    return instance;
  }

  private HttpRequestExecuter() {
  }

  public void init2LeggedOauth(String consumerKey, String consumerSecret, boolean isLiveUrl) {
    isLiveUrl = true;
    consumer2Legged = new DefaultOAuthConsumer(consumerKey, consumerSecret);
  }

  @Override
  public boolean isLiveUrl() {
    return isLiveUrl;
  }


  @Override
  /**
   * executes the {@link RequestUrl} and uses the {@link IS24ResponseHandler} for handling the response
   * @param <Result> the needed result
   * @param urlRequest
   * @param handler instance of {@link IS24ResponseHandler}
   * @return a {@link Response} with the needed result in it
   * @throws SessionInvalidException
   * @throws NoConnectionException
   * @throws SecurityException
   */
  public <Result> Response<Result> execute(RequestUrl urlRequest, IS24ResponseHandler<Result> handler)
    throws NoConnectionException, SecurityException {
    Log.d(TAG, "uri:" + urlRequest.uri);

    Response<Result> result = new Response<Result>();
    HttpURLConnection connection = getHttpConnection(urlRequest, handler);

    try {
      if (urlRequest.security == RequestUrl.OAUTH_2_LEGGED) {
        if (consumer2Legged == null) {
          throw new OAuthException("Two legged consumer has not been initialized!") {
            private static final long serialVersionUID = 1L;
          }
          ;
        }
        consumer2Legged.sign(connection);
      }

    } catch (OAuthException e) {
      throw new SecurityException(SecurityException.SIGNING_ERROR, "cannot sign request", e);
    }

    try {
      connection.connect();

      Response<Result> handleResponse = handler.handleResponse(connection.getInputStream(),
        connection.getResponseCode(), result);
      connection.disconnect();

      if (!handleResponse.success && (handleResponse.statusCode == HttpStatus.SC_UNAUTHORIZED)) {
        throw new SecurityException(SecurityException.NO_AUTHORIZATION, "request unauthorized");
      }

      return handleResponse;
    } catch (UnknownHostException e) {
      throw noConnectionException;
    } catch (SocketException e) {
      Log.d(TAG, "SocketException#message:" + e.getMessage());
      if ("Network unreachable".equals(e.getMessage())) {
        throw noConnectionException;
      }
      Log.e(TAG, "socket exception", e);
    } catch (SocketTimeoutException e) {
      Log.e(TAG, "socket tiemout exception", e);
      throw noConnectionException;
    } catch (IOException e) {
      Log.e(TAG, "io exception", e);
    }

    return result;
  }

  /**
   * returns a {@link HttpUriRequest} for the given HTTP method
   * @param urlRequest
   * @return a {@link HttpUriRequest}
   */
  protected HttpURLConnection getHttpConnection(RequestUrl urlRequest, IS24ResponseHandler handler) {
    HttpURLConnection connection = null;
    try {
      URL url = new URL(urlRequest.uri);
      connection = (HttpURLConnection) url.openConnection();
    } catch (MalformedURLException e1) {
      Log.e(TAG, "Malformed URL.", e1);
    } catch (IOException e) {
      Log.e(TAG, "IOException.", e);
    }
    if (connection != null) {
      HttpHeader acceptHttpHeader = handler.getAcceptHeader();
      if (acceptHttpHeader != null) {
        connection.addRequestProperty(acceptHttpHeader.getName(), acceptHttpHeader.getValue());
      }
      if (handler.hasAcceptEncodingHeader()) {
        HttpHeader acceptEncodingHttpHeader = HttpHeader.ACCEPT_ENCODING_GZIP;
        connection.addRequestProperty(acceptEncodingHttpHeader.getName(), acceptEncodingHttpHeader.getValue());
      }

      switch (urlRequest.method) {
        case RequestUrl.GET: {
          return connection;
        }

        default: {
          throw new IllegalArgumentException("unknown method=" + urlRequest.method);
        }
      }
    }
    return connection;
  }
}
