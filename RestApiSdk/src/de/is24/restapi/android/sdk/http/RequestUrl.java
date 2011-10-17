package de.is24.restapi.android.sdk.http;

/**
 * This Class is a simple request object with an URI and the needed HTTP method.
 * @author Hasan Hosgel
 *
 */
public class RequestUrl {
  public static final byte GET = 1;
  public static final byte NO_SECURITY = 5;
  public static final byte OAUTH_2_LEGGED = 6;
  public final byte method;
  public final byte security;
  public final String uri;

  public RequestUrl(final String uri, final byte method) {
    this.uri = uri;
    this.method = method;
    security = NO_SECURITY;
  }

  public RequestUrl(final String uri, final byte method, final byte security) {
    this.uri = uri;
    this.method = method;
    this.security = security;
  }
}
