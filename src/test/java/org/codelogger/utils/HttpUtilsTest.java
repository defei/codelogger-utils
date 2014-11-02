package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.codelogger.utils.HttpUtils;
import org.codelogger.utils.exceptions.HttpException;

import org.junit.Ignore;
import org.junit.Test;

public class HttpUtilsTest {

    @Test
    public void doGet_withInvalidUrl() {

        try {
            HttpUtils.doGet("http://aa.bb.ddl", 2);
        } catch (Exception e) {
            assertEquals(e.getClass(), HttpException.class);
            println(e.getMessage());
        }
    }

    @Test
    public void doGet_withValidUrl() {

        String response = HttpUtils.doGet("http://www.baidu.com");
        assertTrue(response.contains("baidu"));
    }

    @Ignore
    @Test
    public void doPost() {

        String action = "http://app.ftng.net/p/account/login";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("account", "test@ftng.net");
        parameters.put("password", "test");
        String response = HttpUtils.doPost(action, parameters);
        assertTrue(response.contains("退出"));
    }
}
