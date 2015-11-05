package org.codelogger.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class IOUtils {

  /**
   * 1 KB
   */
  private static final int ONE_KILOBYTE_SIZE = 1024;

  /**
   * 32 KB
   */
  private static final int BUFFER_SIZE = ONE_KILOBYTE_SIZE * 32;

  public static InputStream getInputStreamFromNetwork(final String targetURL) throws IOException {

    return new URL(targetURL).openConnection().getInputStream();
  }

  public static byte[] getBytesFromNetwork(final String targetURL) throws IOException {

    return getBytes(getInputStreamFromNetwork(targetURL));
  }

  /**
   * Get bytes from given input stream.
   *
   * @param sourceInputStream Source inputStream object to be handled.
   * @return bytes from given input stream.
   * @throws IOException
   */
  public static byte[] getBytes(final InputStream sourceInputStream) throws IOException {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[BUFFER_SIZE];
    for (int len = 0; (len = sourceInputStream.read(buffer)) != -1;) {
      byteArrayOutputStream.write(buffer, 0, len);
    }
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    return arrayOfByte;
  }

  /**
   * Write source input stream bytes into destination output stream.
   *
   * @param sourceInputStream input stream to be handled.
   * @param destinationOutputStream output stream to be handled.
   * @return true if write source input stream bytes into destination output
   *         stream success; false otherwise.
   * @throws IOException
   */
  public static boolean write(final InputStream sourceInputStream,
    final OutputStream destinationOutputStream) throws IOException {

    byte[] buffer = buildBuffer(BUFFER_SIZE);
    for (int len = 0; (len = sourceInputStream.read(buffer)) != -1;) {
      destinationOutputStream.write(buffer, 0, len);
    }
    destinationOutputStream.flush();
    return true;
  }

  /**
   * Write source bytes into destination output stream.
   *
   * @param sourceBytes bytes to be handled.
   * @param destinationOutputStream destination output stream to be handled.
   * @return true if write source bytes into destination output stream success.
   * @throws IOException
   */
  public static boolean write(final byte[] sourceBytes, final OutputStream destinationOutputStream)
    throws IOException {

    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(destinationOutputStream);
    bufferedOutputStream.write(sourceBytes, 0, sourceBytes.length);
    bufferedOutputStream.flush();
    return true;
  }

  private static byte[] buildBuffer(final int bufferSize) {

    return new byte[bufferSize];
  }

}
