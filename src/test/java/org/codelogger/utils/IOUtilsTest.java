package org.codelogger.utils;

import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.codelogger.utils.FileUtils;
import org.codelogger.utils.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class IOUtilsTest {

    public final static String testPath = "FileUtilsTest/";

    private final String testReadFileName = "testFile.txt";

    private final String testWriteFileName = "testForWrite.txt";

    private final String noSuchFileOrFolder = "/aaa/No such file or folder!";

    private File testFileForRead;

    private InputStream testInputStream;

    private OutputStream testOutputStream;

    @Before
    public void init() throws FileNotFoundException {

        testFileForRead = new File(testPath + testReadFileName);
        testInputStream = new FileInputStream(testPath + testReadFileName);
        testOutputStream = new FileOutputStream(testPath + testWriteFileName);
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
