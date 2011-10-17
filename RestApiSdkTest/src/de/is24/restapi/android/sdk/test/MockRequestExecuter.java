package de.is24.restapi.android.sdk.test;

import java.util.Stack;
import de.is24.restapi.android.sdk.http.NoConnectionException;
import de.is24.restapi.android.sdk.http.RequestExecuter;
import de.is24.restapi.android.sdk.http.RequestUrl;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.http.response.handler.IS24ResponseHandler;


public class MockRequestExecuter implements RequestExecuter {
  @SuppressWarnings("rawtypes")
  public Stack<Response> results;
  public Stack<RequestUrl> urlRequests;

  @SuppressWarnings("rawtypes")
  public MockRequestExecuter() {
    results = new Stack<Response>();
    urlRequests = new Stack<RequestUrl>();
    results.push(null);
  }

  @SuppressWarnings("unchecked")
  public <Result> Response<Result> execute(RequestUrl urlRequest, IS24ResponseHandler<Result> handler)
    throws NoConnectionException {
    urlRequests.push(urlRequest);
    return results.pop();
  }

  @Override
  public void init2LeggedOauth(String consumerKey, String consumerSecret, boolean isLiveUrl) {
    // Not implemented
  }

  @Override
  public boolean isLiveUrl() {
    return false;
  }
}
