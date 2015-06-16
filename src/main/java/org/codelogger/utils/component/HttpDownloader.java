package org.codelogger.utils.component;

import static com.google.common.collect.Maps.newHashMap;
import static org.codelogger.utils.MapUtils.isNotEmpty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.codelogger.utils.IOUtils;
import org.codelogger.utils.beans.HttpRequest;
import org.codelogger.utils.beans.HttpResponse;
import org.codelogger.utils.exceptions.HttpException;

public class HttpDownloader {

  public HttpResponse doGet(final String url) {

    HttpRequest httpRequest = new HttpRequest(url);
    return doGet(httpRequest);
  }

  public HttpResponse doGet(final String url, final Map<String, String> headers) {

    HttpRequest httpRequest = new HttpRequest(url, headers);
    return doGet(httpRequest);
  }

  public HttpResponse doGet(final HttpRequest httpRequest) {

    HttpGet httpPost = new HttpGet(httpRequest.getUrl());
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse httpResponse = null;
    try {
      if (isNotEmpty(httpRequest.getHeaders())) {
        for (Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
          httpPost.addHeader(entry.getKey(), entry.getValue());
        }
      }
      httpClient = httpRequest.isIgnoreSslCertificate() ? getIgnoreSslCertificateHttpClient()
        : getHttpClient();
      httpResponse = httpClient.execute(httpPost);
      return parseHttpResponse(httpResponse);
    } catch (Exception e) {
      throw new HttpException(e);
    } finally {
      try {
        if (httpResponse != null) {
          httpResponse.close();
        }
      } catch (IOException e) {
      }
      try {
        if (httpClient != null) {
          httpClient.close();
        }
      } catch (IOException e) {
      }
    }
  }

  public HttpResponse doPost(final String url, final Map<String, String> headers, final String body) {

    try {
      HttpRequest httpRequest = new HttpRequest(url, headers, new StringEntity(body));
      return doPost(httpRequest);
    } catch (UnsupportedEncodingException e) {
      throw new HttpException("Encoding failed.", e);
    }

  }

  public HttpResponse doPost(final HttpRequest httpRequest) {

    HttpPost httpPost = new HttpPost(httpRequest.getUrl());
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse httpResponse = null;
    try {
      if (isNotEmpty(httpRequest.getHeaders())) {
        for (Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
          httpPost.addHeader(entry.getKey(), entry.getValue());
        }
      }
      if (httpRequest.getHttpEntity() != null) {
        httpPost.setEntity(httpRequest.getHttpEntity());
      }
      httpClient = httpRequest.isIgnoreSslCertificate() ? getIgnoreSslCertificateHttpClient()
        : getHttpClient();
      httpResponse = httpClient.execute(httpPost);
      return parseHttpResponse(httpResponse);
    } catch (Exception e) {
      throw new HttpException(e);
    } finally {
      try {
        if (httpResponse != null) {
          httpResponse.close();
        }
      } catch (IOException e) {
      }
      try {
        if (httpClient != null) {
          httpClient.close();
        }
      } catch (IOException e) {
      }
    }
  }

  private HttpResponse parseHttpResponse(final CloseableHttpResponse httpResponse)
    throws IOException {

    int statusCode = httpResponse.getStatusLine().getStatusCode();
    Map<String, String> responseHeaders = newHashMap();
    for (Header header : httpResponse.getAllHeaders()) {
      responseHeaders.put(header.getName(), header.getValue());
    }
    byte[] content = IOUtils.getBytes(httpResponse.getEntity().getContent());
    return new HttpResponse(statusCode, responseHeaders, content);
  }

  private CloseableHttpClient getHttpClient() {

    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    CloseableHttpClient httpClient = httpClientBuilder.build();
    return httpClient;
  }

  public CloseableHttpClient getIgnoreSslCertificateHttpClient() {

    SSLContext sslContext = null;
    try {
      sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

        @Override
        public boolean isTrusted(final X509Certificate[] arg0, final String arg1)
          throws CertificateException {

          return true;
        }
      }).build();
    } catch (Exception e) {
      throw new HttpException("can not create http client.", e);
    }
    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
      new NoopHostnameVerifier());
    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
      .<ConnectionSocketFactory> create()
      .register("http", PlainConnectionSocketFactory.getSocketFactory())
      .register("https", sslSocketFactory).build();
    PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(
      socketFactoryRegistry);
    return HttpClientBuilder.create().setSslcontext(sslContext).setConnectionManager(connMgr)
      .build();
  }
}
