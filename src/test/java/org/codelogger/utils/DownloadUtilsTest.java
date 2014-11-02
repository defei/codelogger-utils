package org.codelogger.utils;

import static junit.framework.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.codelogger.utils.DownloadUtils;
import org.junit.Ignore;
import org.junit.Test;

public class DownloadUtilsTest {

    private final String testURL = "http://www.baidu.com";

    @Test
    public void getInputStreamFromHttp_theURLisCorrect_teturnDestinationInputstread()
            throws IOException {

        InputStream inputStreamFromHttp = DownloadUtils.getInputStreamFromHttp(testURL);
        assertNotNull(inputStreamFromHttp);
    }

    @Test
    @Ignore
    public void getInputStreamFromHttp() throws IOException {

        DownloadUtils.getInputStreamFromHttp("http://a.b.c");
    }

    @Test
    public void getBytesFromHttp_theURLIsCorrect_returnDestinationBytes() throws IOException {

        byte[] bytesFromHttp = DownloadUtils.getBytesFromHttp(testURL);
        Assert.assertNotNull(bytesFromHttp);
    }
}
