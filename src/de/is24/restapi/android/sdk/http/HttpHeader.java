package de.is24.restapi.android.sdk.http;

public enum HttpHeader {
  ACCEPT_ENCODING_GZIP(HttpParameters.ACCEPT_Encoding, HttpParameters.GZIP),
  ACCEPT_JSON(HttpParameters.ACCEPT, HttpParameters.JSON), ACCEPT_XML(HttpParameters.ACCEPT, HttpParameters.XML),
  CONTENTTYPE_JSON(HttpParameters.CONTENT_TYPE, HttpParameters.JSON),
  CONTENTTYPE_XML(HttpParameters.CONTENT_TYPE, HttpParameters.XML),
  ACCEPT_JSON_STRICT(HttpParameters.ACCEPT, HttpParameters.JSON_STRICT),
  ACCEPT_HTML(HttpParameters.ACCEPT, HttpParameters.HTML);

  private String headerName;
  private String headerValue;


  private HttpHeader(final String key, final String value) {
    headerName = key;
    headerValue = value;
  }

  public String getName() {
    return headerName;
  }

  public String getValue() {
    return headerValue;
  }

  @Override
  public String toString() {
    return headerName + "=" + headerValue;
  }
}
