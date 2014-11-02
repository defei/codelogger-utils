package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.codelogger.utils.FileUtils;
import org.codelogger.utils.MD5Utils;
import org.junit.Test;

public class MD5UtilsTest {

    private final String adminMD5 = "21232f297a57a5a743894a0e4a801fc3";

    @Test
    public void getMD5String_forString() {

        String test = "admin";
        String adminMD51 = MD5Utils.getMD5("admin");
        assertEquals(adminMD5, adminMD51);
        println("admin md5:%s", adminMD51);
        test = null;
        String nullMD5 = MD5Utils.getMD5(test);
        assertEquals(null, nullMD5);
        println("null md5:%s", nullMD5);
    }

    @Test
    public void getMD5String_forBytes() {

        String test = "admin";
        byte[] testBytes = test.getBytes();
        String adminBytesMD5 = MD5Utils.getMD5(testBytes);
        assertEquals(adminMD5, adminBytesMD5);
        println("admin bytes md5:%s", adminBytesMD5);
        testBytes = null;
        String nullMD5 = MD5Utils.getMD5(testBytes);
        assertEquals(null, nullMD5);
        println("null md5:%s", nullMD5);
    }

    @Test
    public void getFileMD5String() throws IOException {

        String fileMD5 = "0ad6e115f423c8ef1e0cb12e621a896a";
        File file = new File("FileUtilsTest/testFile.txt");
        String fileMD5String = MD5Utils.getMD5(file);
        assertEquals(fileMD5, fileMD5String);
        println(fileMD5String);
    }

    @Test
    public void testFileLock() throws IOException {

        String source = "FileUtilsTest/testFile.txt";
        String destinationPath = "FileUtilsTest/testMd5Lock.txt";
        File destination = new File(destinationPath);
        FileUtils.copy(new File(source), destination);
        MD5Utils.getMD5(new File(destinationPath));
        boolean delete = FileUtils.delete(destinationPath);
        assertTrue(delete);
    }
}
