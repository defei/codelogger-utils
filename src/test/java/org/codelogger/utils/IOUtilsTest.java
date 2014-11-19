package org.codelogger.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

public class IOUtilsTest {

    private final String suffix = "txt";

    private final String noSuchFileOrFolder = "/aaa/No such file or folder!";

    private File testFileForRead;

    private InputStream testInputStream;

    private OutputStream testOutputStream;

    @Before
    public void init() throws IOException {

        testFileForRead = File.createTempFile(StringUtils.getRandomString(21), suffix);
        FileUtils.write("Hello".getBytes(), testFileForRead);
        testInputStream = new FileInputStream(testFileForRead);
        testOutputStream = new FileOutputStream(File.createTempFile(
                StringUtils.getRandomString(21), suffix));
    }

    @Test(expected = FileNotFoundException.class)
    public void getBytes_inputStreamIsIncorrect_throwFileNotFoundException() throws IOException {

        FileInputStream fis = new FileInputStream(buildIncorrectFile());
        IOUtils.getBytes(fis);
    }

    @Test
    public void getBytes_inputStreamIsCorrect_returnBytes() throws IOException {

        byte[] bytes = IOUtils.getBytes(testInputStream);
        assertTrue(bytes.length > 0);
        testInputStream.close();
    }

    @Test(expected = FileNotFoundException.class)
    public void write_inputStreamIsIncorrectAndOutputStreamIsCorrect_throwFileNotFoundException()
            throws IOException {

        try {
            testInputStream = new FileInputStream(noSuchFileOrFolder);
            IOUtils.write(testInputStream, testOutputStream);
        } finally {
            testOutputStream.close();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void write_outputStreamIsIncorrectAndinputStreamIsCorrect_throwFileNotFoundException()
            throws IOException {

        try {
            testOutputStream = new FileOutputStream(noSuchFileOrFolder);
            IOUtils.write(testInputStream, testOutputStream);
        } finally {
            testInputStream.close();
        }
    }

    @Test
    public void write_inputStreamAndOutputStreamBothCorrect_returnTrue() throws IOException {

        try {
            boolean writeSuccess = IOUtils.write(testInputStream, testOutputStream);
            assertTrue(writeSuccess);
        } finally {
            testInputStream.close();
            testOutputStream.close();
        }
    }

    @Test
    public void write_sourceBytesAndDestinationOutPutStreamBothCorrect_runSuccess()
            throws IOException {

        byte[] sourceBytes = FileUtils.getBytes(testFileForRead);
        IOUtils.write(sourceBytes, testOutputStream);
        testOutputStream.close();
    }

    private File buildIncorrectFile() {

        return new File(noSuchFileOrFolder);
    }
}
