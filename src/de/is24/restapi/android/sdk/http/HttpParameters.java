package de.is24.restapi.android.sdk.http;

/**
 * holds the needed parameters, which should be used within the services with HTTP.
 * @author Hasan Hosgel
 *
 */
public interface HttpParameters {
  String HTML = "text/html";
  String JSON = "application/json";
  String JSON_STRICT = "application/json;strict=true";
  String XML = "application/xml";
  String GZIP = "gzip";
  String ACCEPT = "Accept";
  String ACCEPT_Encoding = "Accept-Encoding";
  String CONTENT_TYPE = "Content-Type";
  String IF_NONE_MATCH = "If-None-Match";
  String ETAG = "ETag";
}
