package de.is24.restapi.android.sdk.http;

import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.http.response.handler.IS24ResponseHandler;


/**
 * An interface for the {@link RequestExecuter}
 * @author Hasan Hosgel
 *
 */
public interface RequestExecuter {
  <Result> Response<Result> execute(RequestUrl urlRequest, IS24ResponseHandler<Result> handler)
    throws NoConnectionException, SecurityException;

  void init2LeggedOauth(String consumerKey, String consumerSecret, boolean isLiveUrl);

  boolean isLiveUrl();


}
