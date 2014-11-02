package org.codelogger.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtils {

    private static final int ONE_KILOBYTE_SIZE = 1024;

    @SuppressWarnings("unused")
    private static final int ONE_MEGABYTE_SIZE = ONE_KILOBYTE_SIZE * 1024;

    private static final int BUFFER_SIZE = ONE_KILOBYTE_SIZE * 32;

    @SuppressWarnings("unused")
    private static final int REFELCT_BUFFER_SIZE = ONE_KILOBYTE_SIZE * 64;

    private DownloadUtils() {

    }

    /**
     * Get destination web file input stream.
     * 
     * @param httpFileURL
     *            the web file url which you want to download.
     * @return the destination page input stream.
     * @throws IOException
     */
    public static InputStream getInputStreamFromHttp(String httpFileURL) throws IOException {

        URLConnection urlConnection = null;
        urlConnection = new URL(httpFileURL).openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    /**
     * Get destination web file bytes.
     * 
     * @param httpFileURL
     *            the web file url which you want to download.
     * @return the destination file bytes.
     * @throws IOException
     */
    public static byte[] getBytesFromHttp(String httpFileURL) throws IOException {

        InputStream bufferedInputStream = null;
        try {
            bufferedInputStream = getInputStreamFromHttp(httpFileURL);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = 0; (len = bufferedInputStream.read(buffer)) != -1;) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byte[] arrayOfByte1 = byteArrayOutputStream.toByteArray();
            return arrayOfByte1;
        } finally {
            if (bufferedInputStream != null)
                bufferedInputStream.close();
        }
    }
}