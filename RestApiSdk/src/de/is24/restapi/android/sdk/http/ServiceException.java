package de.is24.restapi.android.sdk.http;

/**
 * This Exception is thrown by the services
 * @author Hasan Hosgel
 *
 */
public class ServiceException extends Exception {
  private static final long serialVersionUID = -1421640664577471838L;
  public static final byte UNKNOWN_ERROR = 0;
  public static final byte INVALID_SESSION = 1;
  public static final byte SENDING_ERROR = 2;
  public static final byte PARSING_ERROR = 3;
  public static final byte NOT_FOUND = 4;
  public static final byte UNKNOWN_TYPE = 5;
  public static final byte DIRECTORY_FULL = 6;
  public static final byte IO_ERROR = 7;

  public final byte errorCode;

  public ServiceException(final byte errorCode, final CharSequence message) {
    super((message == null) ? null : message.toString());
    this.errorCode = errorCode;
  }

  public ServiceException(final byte errorCode, final CharSequence message, final Throwable e) {
    super((message == null) ? null : message.toString(), e);
    this.errorCode = errorCode;
  }

  public ServiceException(final byte errorCode, final String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public ServiceException(final byte errorCode, final String message, final Throwable e) {
    super(message, e);
    this.errorCode = errorCode;
  }

}
