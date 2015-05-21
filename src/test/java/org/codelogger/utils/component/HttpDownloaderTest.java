package org.codelogger.utils.component;

import static org.codelogger.utils.ArrayUtils.isNotEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.codelogger.utils.beans.HttpResponse;
import org.junit.Test;

public class HttpDownloaderTest {

  private static final String UTF_8 = "UTF-8";

  private HttpDownloader httpDownloader = new HttpDownloader();

  private String url = "http://www.baidu.com";

  @Test
  public void doGet() {

    HttpResponse httpResponse = httpDownloader.doGet(url, null);
    httpResponse.getContentAsString(UTF_8);
    Integer expectedStatusCode = 200;
    assertEquals(expectedStatusCode, httpResponse.getStatusCode());
    assertTrue(isNotEmpty(httpResponse.getContent()));
  }
}
