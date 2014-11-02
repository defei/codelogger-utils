package org.codelogger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageReader;

import org.codelogger.utils.ImageUtils.ImageReaderUtils;

import org.junit.Test;

public class ImageUtilsTest {

    String imagePath = FileUtilsTest.testPath + "imageForTest.png";

    @Test
    public void getFormatName() throws IOException {

        String expectedName = "png";
        File sourceImage = new File(imagePath);
        String name = ImageUtils.getFormatName(sourceImage);
        assertEquals(expectedName, name);
    }

    @Test
    public void getImageReader() throws IOException {

        int expectedWith = 53;
        int expectedHeight = 27;
        File sourceImage = new File(imagePath);
        ImageReader imageReader = ImageUtils.getImageReader(sourceImage);
        int width = imageReader.getWidth(0);
        assertEquals(expectedWith, width);
        int height = imageReader.getHeight(0);
        assertEquals(expectedHeight, height);
    }

    @Test
    public void getImageReaderFromHttpImage() throws IOException {

        String sourceImageHttpURL = "http://img3.cache.netease.com/www/logo/logo_png.png";
        ImageReader imageReaderFromHttpImage = ImageUtils
                .getImageReaderFromHttpImage(sourceImageHttpURL);
        assertNotNull(imageReaderFromHttpImage);
    }

    @Test
    public void getImageReaderFromHttpImage1() throws IOException {

        String expectedName = "png";
        String sourceImageHttpURL = "http://img3.cache.netease.com/www/logo/logo_png.png";
        String formatNameFromHttpImage = ImageUtils.getFormatNameFromHttpImage(sourceImageHttpURL);
        assertEquals(expectedName, formatNameFromHttpImage);
        String formatNameFromHttpImage1 = ImageUtils
                .getFormatNameFromHttpImage("http://www.baidu.com");
        assertEquals(null, formatNameFromHttpImage1);
    }

    @Test
    public void getWidthAndHeight() throws IOException {

        int expectedWith = 53;
        int expectedHeight = 27;
        File sourceImage = new File(imagePath);
        ImageReader imageReader = ImageUtils.getImageReader(sourceImage);
        int width = ImageReaderUtils.getWidth(imageReader);
        assertEquals(expectedWith, width);
        int height = ImageReaderUtils.getHeight(imageReader);
        assertEquals(expectedHeight, height);
    }

    @Test
    public void snapshot() throws IOException {

        imagePath = FileUtilsTest.testPath + "gifForTest.gif";
        String gifSnapshotPath = FileUtilsTest.testPath + "gifForTestSnapshot.jpg";
        byte[] imageBytesFromHttp = IOUtils.getBytes(new FileInputStream(new File(imagePath)));
        Color backgroundColor = new Color(0, 0, 0);
        byte[] snapshotBytes = ImageUtils.snapshot(imageBytesFromHttp, backgroundColor);
        FileUtils.write(new ByteArrayInputStream(snapshotBytes), gifSnapshotPath);
    }
}
