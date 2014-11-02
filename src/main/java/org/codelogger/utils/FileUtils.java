package org.codelogger.utils;

import static org.codelogger.utils.ExceptionUtils.iae;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A useful tools to handle files, likes judge exist\folder, get file, get file
 * bytes, writ file, copy files, and so on...
 * 
 * @author DengDefei
 * 
 */
public class FileUtils {

    /**
     * 1 KB
     */
    private static final int ONE_KILOBYTE_SIZE = 1024;

    /**
     * 32 KB
     */
    private static final int BUFFER_SIZE = ONE_KILOBYTE_SIZE * 32;

    /**
     * 64 KB
     */
    private static final int REFELCT_BUFFER_SIZE = ONE_KILOBYTE_SIZE * 64;

    /**
     * Tests whether the file or directory denoted by this abstract pathname
     * exists.
     * 
     * @param destinationFile
     * @return if and only if the file or directory denoted by this abstract
     *         pathname exists return true.
     */
    public static boolean isExist(final File destinationFile) {

        return destinationFile.exists();
    }

    /**
     * Tests whether the file or directory denoted by this abstract pathname
     * exists.
     * 
     * @param destinationPath
     *            destination file path or directory path.
     * @return if and only if the file or directory denoted by this abstract
     *         pathname exists return true.
     */
    public static boolean isExist(final String destinationPath) {

        File file = buildFile(destinationPath);
        return file.exists();
    }

    /**
     * Tests whether the file denoted by this abstract pathname is a directory.
     * Where it is required to distinguish an I/O exception from the case that
     * the file is not a directory, or where several attributes of the same file
     * are required at the same time, then the Files.readAttributes method may
     * be used.
     * 
     * @param destinationFile
     *            file object to be tested.
     * @return if and only if the file denoted by this abstract pathname exists
     *         and is a directory return true.
     */
    public static boolean isDirectory(final File destinationFile) {

        return destinationFile.isDirectory();
    }

    /**
     * Tests whether the file denoted by this abstract pathname is a directory.
     * Where it is required to distinguish an I/O exception from the case that
     * the file is not a directory, or where several attributes of the same file
     * are required at the same time, then the Files.readAttributes method may
     * be used.
     * 
     * @param destinationPath
     *            string path to be tested.
     * @return if and only if the file denoted by this abstract pathname exists
     *         and is a directory return true.
     */
    public static boolean isDirectory(final String destinationPath) {

        return buildFile(destinationPath).isDirectory();
    }

    /**
     * Creates the directory named by this abstract pathname, including any
     * necessary but nonexistent parent directories.
     * 
     * @param destinationFile
     * @return if and only if the directory was created, along with all
     *         necessary parent directories return true.
     */
    public static boolean createDirectory(final File destinationFile) {

        return isExist(destinationFile) ? true : destinationFile.mkdirs();
    }

    /**
     * Creates the directory named by this abstract pathname, including any
     * necessary but nonexistent parent directories.
     * 
     * @param destinationPath
     *            string path to be handled.
     * @return if and only if the directory was created, along with all
     *         necessary parent directories return true.
     */
    public static boolean createDirectory(final String destinationPath) {

        File destinationFile = buildFile(destinationPath);
        return isExist(destinationFile) ? true : destinationFile.mkdirs();
    }

    /**
     * Get bytes from given source file.
     * 
     * @param source
     *            file to be handled.
     * @return bytes from given source file.
     * @throws IOException
     */
    public static byte[] getBytes(final File source) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(source);
        return getBytesFromInputStreamAndCloseIt(fileInputStream);
    }

    /**
     * Get bytes from given file input stream, and close the file input stream.
     * 
     * @param fileInputStream
     *            file input stream to be handled.
     * @return bytes from given file input stream.
     * @throws IOException
     */
    private static byte[] getBytesFromInputStreamAndCloseIt(final FileInputStream fileInputStream)
            throws IOException {

        try {
            return IOUtils.getBytes(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    /**
     * Get bytes from given source file path.
     * 
     * @param sourcePath
     *            string path to be handled.
     * @return bytes from given source file path.
     * @throws IOException
     */
    public static byte[] getBytes(final String sourcePath) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(sourcePath);
        return getBytesFromInputStreamAndCloseIt(fileInputStream);
    }

    /**
     * Return all files in given folder.<br>
     * 返回给定目录下的所有文件，不包括基子目录下的文件。
     * 
     * @param folderPath
     * @return
     */
    public static File[] getAllFilesOfFolder(final String folderPath) {

        File folder = buildFile(folderPath);
        File[] listFiles = folder.listFiles();
        return listFiles;
    }

    /**
     * Write source input stream bytes into destination file.
     * 
     * @param sourceInputStream
     *            input stream to be handled.
     * @param destinationFile
     *            destination file to be handled.
     * @return true if write source input stream bytes into destination file
     *         success.
     * @throws IOException
     */
    public static boolean write(final InputStream sourceInputStream, final File destinationFile)
            throws IOException {

        OutputStream outputStream = null;
        try {
            outputStream = buildBufferOutputStream(destinationFile, BUFFER_SIZE);
            return IOUtils.write(sourceInputStream, outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Write source input stream bytes into a file which file path is
     * destinationPath.
     * 
     * @param sourceInputStream
     *            input stream to be handled.
     * @param destinationPath
     *            destination file path to be handled.
     * @return true if write source input stream bytes into a file which file
     *         path is destinationPath success.
     * @throws IOException
     */
    public static boolean write(final InputStream sourceInputStream, final String destinationPath)
            throws IOException {

        OutputStream outputStream = null;
        try {
            outputStream = buildBufferOutputStream(destinationPath, BUFFER_SIZE);
            return IOUtils.write(sourceInputStream, outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Write source file bytes into destination output stream.
     * 
     * @param sourceFile
     *            source file to be handled.
     * @param destinationOutputStream
     *            destination output stream to be handled.
     * @return true if write source file bytes into destination output stream
     *         success.
     * @throws IOException
     */
    public static boolean write(final File sourceFile, final OutputStream destinationOutputStream)
            throws IOException {

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourceFile), BUFFER_SIZE);
            return IOUtils.write(inputStream, destinationOutputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * Write source file bytes into a file which file path is destinationPath.
     * 
     * @param sourceFile
     *            source file to be handled.
     * @param destinationFile
     *            destination file to be handled.
     * @return true if write source file bytes into a file which file path is
     *         destinationPath success.
     * @throws IOException
     */
    public static boolean write(final File sourceFile, final File destinationFile)
            throws IOException {

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourceFile), BUFFER_SIZE);
            return write(inputStream, destinationFile);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * Write source file bytes into a file which file path is destinationPath.
     * 
     * @param sourceFile
     *            source file to be handled.
     * @param destinationPath
     *            destination file string path to be handled.
     * @return true if write source file bytes into a file which file path is
     *         destinationPath success.
     * @throws IOException
     */
    public static boolean write(final File sourceFile, final String destinationPath)
            throws IOException {

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourceFile), BUFFER_SIZE);
            boolean bool = write(inputStream, destinationPath);
            return bool;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * Write source input stream into a file which file path is
     * destinationPath(The source input stream must have a method called read
     * and return bytes).
     * 
     * @param sourceInputStream
     *            source input stream to be handled.
     * @param destinationPath
     *            destination file string path to be handled.
     * @return true if write success.
     * @throws IOException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static boolean write(final Object sourceInputStream, final String destinationPath)
            throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        OutputStream outputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            Class<?> clazz = sourceInputStream.getClass();
            Method method = MethodUtils.findMethod(clazz, "read", new Class[] { byte[].class });
            method.setAccessible(true);
            fileOutputStream = new FileOutputStream(destinationPath);
            outputStream = new BufferedOutputStream(fileOutputStream, REFELCT_BUFFER_SIZE);
            // outputStream = buildBufferOutputStream(destinationPath,
            // REFELCT_BUFFER_SIZE);
            byte[] buffer = buildBuffer(REFELCT_BUFFER_SIZE);
            for (Integer len = Integer.valueOf(0); (len = (Integer) method.invoke(
                    sourceInputStream, new Object[] { buffer })).intValue() != -1;)
                outputStream.write(buffer, 0, len.intValue());
            return true;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    /**
     * Write source bytes into destination file.
     * 
     * @param sourceBytes
     *            source bytes to be handled.
     * @param destinationFile
     *            destination file to be handles.
     * @return true if write source bytes into destination file success.
     * @throws IOException
     */
    public static boolean write(final byte[] sourceBytes, final File destinationFile)
            throws IOException {

        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));
            bufferedOutputStream.write(sourceBytes);
        } finally {
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        }
        return false;
    }

    /**
     * Write source bytes into a file which file path is destination.
     * 
     * @param sourceBytes
     *            source bytes to be handled.
     * @param destinationPath
     *            destination file string path to be handled.
     * @return true if write source bytes into a file which file path is
     *         destination success.
     * @throws IOException
     */
    public static boolean write(final byte[] sourceBytes, final String destinationPath)
            throws IOException {

        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destinationPath));
            bufferedOutputStream.write(sourceBytes);
        } finally {
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        }
        return true;
    }

    /**
     * Deletes the file or directory denoted by this abstract pathname.
     * 
     * @param destinationPath
     *            file string path to be handled.
     * @return true if deletes the file or directory denoted by this abstract
     *         pathname success; false otherwise.
     */
    public static boolean delete(final String destinationPath) {

        return delete(buildFile(destinationPath));
    }

    /**
     * Deletes the file or directory denoted by this abstract file.
     * 
     * @param destinationFile
     * @return true if delete file or directory success; false otherwise;
     */
    public static boolean delete(final File destinationFile) {

        if ((destinationFile != null) && (destinationFile.exists())) {
            if (destinationFile.isDirectory()) {
                File[] children = destinationFile.listFiles();
                if (children != null) {
                    for (int i = 0; i < children.length; i++) {
                        delete(children[i]);
                    }
                }
            }
            return destinationFile.delete();
        }
        return false;
    }

    /**
     * Copy source file or directory into destination file or directory.
     * 
     * @param source
     *            source file or directory.
     * @param destination
     *            destination file or directory.
     * @return true if copy success; false otherwise;
     * @throws IOException
     */
    public static boolean copy(final File source, final File destination) throws IOException {

        return copyDirectory(source, destination);
    }

    /**
     * Copy source file or directory into destination file or directory.
     * 
     * @param source
     *            source file or directory.
     * @param destination
     *            destination file or directory.
     * @return true if copy success; false otherwise;
     * @throws IOException
     */
    private static boolean copyDirectory(final File source, final File destination)
            throws IOException {

        if (source.isDirectory()) {
            destination.mkdir();
            File[] entries = source.listFiles();
            iae.throwIfNull(entries, "Could not list files in directory: %s", source);
            for (int i = 0; i < entries.length; i++) {
                File file = entries[i];
                copyDirectory(file, new File(destination, file.getName()));
            }
            return true;
        } else if (source.isFile()) {
            try {
                destination.createNewFile();
            } catch (IOException ex) {
                IOException ioex = new IOException("Failed to create file: " + destination);
                ioex.initCause(ex);
                throw ioex;
            }
            return write(source, destination);
        }
        return false;
    }

    private static BufferedOutputStream buildBufferOutputStream(final String destinationPath,
            final int bufferSize) throws FileNotFoundException {

        return new BufferedOutputStream(new FileOutputStream(destinationPath), bufferSize);
    }

    private static BufferedOutputStream buildBufferOutputStream(final File destinationFile,
            final int bufferSize) throws FileNotFoundException {

        return new BufferedOutputStream(new FileOutputStream(destinationFile), bufferSize);
    }

    private static byte[] buildBuffer(final int bufferSize) {

        return new byte[bufferSize];
    }

    private static File buildFile(final String destinationPath) {

        return new File(destinationPath);
    }
}