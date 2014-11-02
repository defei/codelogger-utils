package org.codelogger.utils;

import static java.lang.String.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codelogger.utils.exceptions.HttpException;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36";

    private HttpUtils() {

    }

    /**
     * access given url by get request without retry when exception.
     * 
     * @param url
     *            url to access
     * @return response content of target url
     */
    public static String doGet(final String url) {

        CloseableHttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            return EntityUtils.toString(httpClient.execute(httpGet).getEntity());
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    private static CloseableHttpClient getHttpClient() {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        return httpClient;
    }

    /**
     * access given url by get request, if get exception, will retry by given
     * retryTimes.
     * 
     * @param url
     *            url to access
     * @param retryTimes
     *            retry times when get exception.
     * @return response content of target url.
     */
    public static String doGet(final String url, final int retryTimes) {

        try {
            return doGetByLoop(url, retryTimes);
        } catch (HttpException e) {
            throw new HttpException(format(
                    "Failed to download content for url: '%s'. Tried '%s' times", url,
                    Math.max(retryTimes + 1, 1)));
        }
    }

    private static String doGetByLoop(final String url, final int retryTimes) {

        try {
            return doGet(url);
        } catch (HttpException e) {
            if (retryTimes > 0) {
                return doGetByLoop(url, retryTimes - 1);
            } else {
                throw e;
            }
        }
    }

    /**
     * access given action with given parameters(<strong>default encoding by
     * "UTF-8"</strong>) by post request without retry when exception.
     * 
     * @param action
     *            action url to access
     * @param parameters
     *            parameters to post
     * @return response content of target action url
     */
    public static String doPost(final String action, final Map<String, String> parameters) {

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Entry<String, String> nameValuePair : parameters.entrySet()) {
            nvps.add(new BasicNameValuePair(nameValuePair.getKey(), nameValuePair.getValue()));
        }
        HttpPost httpPost = new HttpPost(action);
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            CloseableHttpClient httpClient = getHttpClient();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }

    /**
     * access given action with given parameters by post, if get exception, will
     * retry by given retryTimes.
     * 
     * @param action
     *            action url to access
     * @param parameters
     *            parameters to post
     * @param retryTimes
     *            retry times when get exception.
     * @return response content of target action url.
     */
    public static String doPost(final String action, final Map<String, String> parameters,
            final int retryTimes) {

        try {
            return doPostByLoop(action, parameters, retryTimes);
        } catch (HttpException e) {
            throw new HttpException(
                    format("Failed to download content for action url: '%s' with parameters. Tried '%s' times",
                            action, parameters, Math.max(retryTimes + 1, 1)));
        }
    }

    private static String doPostByLoop(final String action, final Map<String, String> parameters,
            final int retryTimes) {

        try {
            return doPost(action, parameters);
        } catch (HttpException e) {
            if (retryTimes > 0) {
                return doPost(action, parameters, retryTimes - 1);
            } else {
                throw e;
            }
        }
    }

}
