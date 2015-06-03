package org.codelogger.utils.beans;

import java.util.Map;

import org.apache.http.entity.AbstractHttpEntity;

public class HttpRequest {

  private String url;

  private Boolean ignoreSslCertificate = false;

  private Map<String, String> headers;

  private AbstractHttpEntity httpEntity;

  public HttpRequest() {

  }

  public HttpRequest(final String url) {

    this.url = url;
  }

  public HttpRequest(final String url, final Boolean ignoreSslCertificate) {

    this.url = url;
    this.ignoreSslCertificate = ignoreSslCertificate;
  }

  public HttpRequest(final String url, final Map<String, String> headers) {

    this.url = url;
    this.headers = headers;
  }

  public HttpRequest(final String url, final Map<String, String> headers,
    final AbstractHttpEntity httpEntity) {

    this.url = url;
    this.headers = headers;
    this.httpEntity = httpEntity;
  }

  public HttpRequest(final String url, final Map<String, String> headers,
    final AbstractHttpEntity httpEntity, final Boolean ignoreSslCertificate) {

    this.url = url;
    this.ignoreSslCertificate = ignoreSslCertificate;
    this.httpEntity = httpEntity;
    this.headers = headers;
  }

  public String getUrl() {

    return url;
  }

  public void setUrl(final String url) {

    this.url = url;
  }

  public Boolean isIgnoreSslCertificate() {

    return ignoreSslCertificate;
  }

  public void setIgnoreSslCertificate(final Boolean ignoreSslCertificate) {

    this.ignoreSslCertificate = ignoreSslCertificate;
  }

  public Map<String, String> getHeaders() {

    return headers;
  }

  public void setHeaders(final Map<String, String> headers) {

    this.headers = headers;
  }

  public AbstractHttpEntity getHttpEntity() {

    return httpEntity;
  }

  public void setHttpEntity(final AbstractHttpEntity httpEntity) {

    this.httpEntity = httpEntity;
  }

}
