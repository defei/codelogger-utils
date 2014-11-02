package org.codelogger.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * A useful tools to handle images, likes get imageReader from file or web URL,
 * get image format name, and so on...
 * 
 * @author DengDefei
 * 
 */
public class ImageUtils {

    private ImageUtils() {

    }

    /**
     * Get image reader from given source image.
     * 
     * @param sourceImage
     *            the image object you want to handle.
     * @return an image read object has given source image data.
     * @throws IOException
     */
    public static ImageReader getImageReader(final Object sourceImage) throws IOException {

        ImageInputStream imageInputStream = ImageIO.createImageInputStream(sourceImage);
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
        ImageReader destinationImageReader = null;
        if (imageReaders.hasNext()) {
            destinationImageReader = imageReaders.next();
            setImageReaderHaveInputStream(destinationImageReader, imageInputStream);
        }
        return destinationImageReader;
    }

    /**
     * Get image reader from given web image.
     * 
     * @param sourceImageHttpURL
     *            the web image object you want to handle.
     * @return an image read object has given source image data.
     * @throws IOException
     */
    public static ImageReader getImageReaderFromHttpImage(final String sourceImageHttpURL)
            throws IOException {

        InputStream inputStream = new URL(sourceImageHttpURL).openConnection().getInputStream();
        return getImageReader(inputStream);
    }

    /**
     * Returns a String identifying the format of the input source.<br>
     * e.g: image source is "<span style='color:blue'>example.png</span>" then
     * return "<span style='color:blue'>png</span>". Even the name just is
     * "<span style='color:blue'>example</span>", we can handle it if this is an
     * image.
     * 
     * @param sourceImage
     *            the image which you want to handle.
     * @return the format name of given image.
     * @throws IOException
     */
    public static String getFormatName(final Object sourceImage) throws IOException {

        return getImageReader(sourceImage).getFormatName();
    }

    /**
     * Returns a String identifying the format of given web image.<br>
     * e.g: image source is "<span style='color:blue'>example.png</span>" then
     * return "<span style='color:blue'>png</span>". Even the name just is
     * "<span style='color:blue'>example</span>", we can handle it if this is an
     * image.
     * 
     * @param sourceImageHttpURL
     *            the web image url which you want to handle.
     * @return the format name of given image.
     * @throws IOException
     */
    public static String getFormatNameFromHttpImage(final String sourceImageHttpURL)
            throws IOException {

        ImageReader imageReaderFromHttpImage = getImageReaderFromHttpImage(sourceImageHttpURL);
        return imageReaderFromHttpImage != null ? imageReaderFromHttpImage.getFormatName() : null;
    }

    public static byte[] snapshot(final byte[] bytes, final Color backgroundColor)
            throws IOException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        BufferedImage newBi = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) newBi.getGraphics();
        g2d.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(),
                backgroundColor, null);
        ByteArrayOutputStream osByteArray = new ByteArrayOutputStream();
        ImageOutputStream outputStream = ImageIO.createImageOutputStream(osByteArray);
        ImageIO.write(newBi, "jpg", outputStream);
        outputStream.flush();
        outputStream.close();
        return osByteArray.toByteArray();
    }

    private static void setImageReaderHaveInputStream(final ImageReader imageReader,
            final ImageInputStream imageInputStream) throws IOException {

        imageReader.setInput(imageInputStream);
    }

    /**
     * A tool to handle image reader.
     * 
     * @author DengDefei
     * 
     */
    public static class ImageReaderUtils {

        private ImageReaderUtils() {

        }

        /**
         * Get image width.
         * 
         * @param imageReader
         *            an imageReader object.
         * @return the width of given image.
         * @throws IOException
         */
        public static int getWidth(final ImageReader imageReader) throws IOException {

            return imageReader.getWidth(0);
        }

        /**
         * Get image height.
         * 
         * @param imageReader
         *            an imageReader object.
         * @return the height of given image.
         * @throws IOException
         */
        public static int getHeight(final ImageReader imageReader) throws IOException {

            return imageReader.getHeight(0);
        }
    }
}