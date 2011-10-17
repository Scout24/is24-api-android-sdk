package de.is24.restapi.android.sdk.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;

import de.is24.restapi.android.sdk.http.HttpParameters;
import de.is24.restapi.android.sdk.http.HttpRequestExecuter;
import de.is24.restapi.android.sdk.http.RequestExecuter;


public class NetworkTestHelper {
  public static BasicHttpResponse createHttpResponse(int status) {
    return createHttpResponse(status, (byte[]) null, false);
  }

  public static BasicHttpResponse createHttpResponse(int httpStatusCode, String resultText) {
    try {
      return createHttpResponse(httpStatusCode, resultText.getBytes("UTF-8"), false);
    } catch (UnsupportedEncodingException e) {
      //cannot occur
    }
    return null;
  }

  public static BasicHttpResponse createHttpResponse(int httpStatusCode, byte[] array) {
    return createHttpResponse(httpStatusCode, array, false);
  }

  public static BasicHttpResponse createHttpResponse(int httpStatusCode, byte[] array, boolean isGzip) {
    BasicHttpResponse response = new BasicHttpResponse(new ProtocolVersion("", 0, 0), httpStatusCode, null);
    if (array != null) {
      BasicHttpEntity entity = new BasicHttpEntity();
      entity.setContent(new ByteArrayInputStream(array));
      entity.setContentLength(array.length);
      if (isGzip) {
        entity.setContentEncoding(HttpParameters.GZIP);
      }
      response.setEntity(entity);
    }

    return response;
  }

  public static void setHttpExecuter(RequestExecuter executer) {
    HttpRequestExecuter.instance = executer;
  }

  public static InputStream createResponseInputStream(String responseContent) throws UnsupportedEncodingException {
    InputStream responseInputStream = null;
    if (responseContent != null) {
      byte[] bytes = responseContent.getBytes("UTF-8");
      responseInputStream = new ByteArrayInputStream(bytes);
    }
    return responseInputStream;
  }

}
