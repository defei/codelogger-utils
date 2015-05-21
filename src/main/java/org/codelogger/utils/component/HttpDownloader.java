package org.codelogger.utils.component;

import static com.google.common.collect.Maps.newHashMap;
import static org.codelogger.utils.MapUtils.isNotEmpty;
import static org.codelogger.utils.StringUtils.isNotBlank;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codelogger.utils.IOUtils;
import org.codelogger.utils.beans.HttpResponse;
import org.codelogger.utils.exceptions.HttpException;

public class HttpDownloader {

  public HttpResponse doGet(final String url, final Map<String, String> headers) {

    HttpGet httpPost = new HttpGet(url);
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse httpResponse = null;
    try {
      if (isNotEmpty(headers)) {
        for (Entry<String, String> entry : headers.entrySet()) {
          httpPost.addHeader(entry.getKey(), entry.getValue());
        }
      }
      httpClient = getHttpClient();
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

    HttpPost httpPost = new HttpPost(url);
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse httpResponse = null;
    try {
      if (isNotEmpty(headers)) {
        for (Entry<String, String> entry : headers.entrySet()) {
          httpPost.addHeader(entry.getKey(), entry.getValue());
        }
      }
      if (isNotBlank(body)) {
        httpPost.setEntity(new StringEntity(body));
      }
      httpClient = getHttpClient();
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
}
