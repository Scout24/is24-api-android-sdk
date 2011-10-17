package de.is24.restapi.android.sdk.http.response;


/**
 * This class is a response from the {@link CustomService}.
 * @author Hasan Hosgel
 *
 * @param <Result> the needed result.
 */
public class Response<Result> {
  public Result result;
  public int statusCode;
  public String optionalMessage;
  public boolean success = false;
}
