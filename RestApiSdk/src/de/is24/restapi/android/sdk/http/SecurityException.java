package de.is24.restapi.android.sdk.http;

public class SecurityException extends ServiceException {
  public static final byte SIGNING_ERROR = 8;
  public static final byte NO_AUTHORIZATION = 9;

  private static final long serialVersionUID = 4355915646374041720L;

  public SecurityException(byte errorCode, CharSequence message, Throwable e) {
    super(errorCode, message, e);
  }

  public SecurityException(byte errorCode, CharSequence message) {
    super(errorCode, message);
  }

  public SecurityException(byte errorCode, String message) {
    super(errorCode, message);
  }

  public SecurityException(byte errorCode, String message, Throwable e) {
    super(errorCode, message, e);
  }
}
