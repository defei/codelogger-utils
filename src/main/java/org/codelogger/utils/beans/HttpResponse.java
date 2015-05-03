package org.codelogger.utils.beans;

import static java.lang.String.format;
import static org.codelogger.utils.ArrayUtils.isEmpty;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.codelogger.utils.exceptions.EncodingException;

public class HttpResponse {

  private Integer statusCode;

  private Map<String, String> headers;

  private byte[] content;

  public HttpResponse(final Integer statusCode, final Map<String, String> headers,
    final byte[] content) {

    super();
    this.statusCode = statusCode;
    this.headers = headers;
    this.content = content;
  }

  public Integer getStatusCode() {

    return statusCode;
  }

  public Map<String, String> getHeaders() {

    return headers;
  }

  public byte[] getContent() {

    return content;
  }

  public String getContentAsString() {

    try {
      return isEmpty(content) ? "" : new String(content, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new EncodingException("Got an exception when convert content to utf-8 string.", e);
    }
  }

  @Override
  public String toString() {

    return format("StatusCode:%s, Headers:%s, Content:%s", statusCode, headers,
      getContentAsString());
  }

}
