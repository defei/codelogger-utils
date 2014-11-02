package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.codelogger.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {

    public final static String testPath = "FileUtilsTest/";

    private final String testReadFileName = "testFile.txt";

    private final String testWriteFileName = "testForWrite.txt";

    private final String testFolderName = "testFolderForRead";

    private final String testFolderNameForDelete = "testFolderForDelete";

    private final String noSuchFileOrFolder = "/aaa/No such file or folder!";

    private String testFilePath;

    private String testWritePath;

    private String testFolderPath;

    private String testFolderPathForRead;

    private String testFolderPathForDelete;

    private File testFileForRead;

    private File testFileForWrite;

    private File testFolderForRead;

    private File testFolderForDelete;

    private InputStream testInputStream;

    private OutputStream testOutputStream;

    @Before
    public void init() throws FileNotFoundException {

        testFilePath = testPath + testReadFileName;
        testWritePath = testPath + testWriteFileName;
        testFolderPath = testPath + testFolderName;
        testFolderPathForRead = testPath + testFolderName;
        testFolderPathForDelete = testPath + testFolderNameForDelete;
        testFileForRead = new File(testPath + testReadFileName);
        testFileForWrite = new File(testPath + testWriteFileName);
        testFolderForRead = new File(testFolderPathForRead);
        testFolderForDelete = new File(testFolderPathForDelete);
        testInputStream = new FileInputStream(testPath + testReadFileName);
        testOutputStream = new FileOutputStream(testPath + testWriteFileName);
    }

    @Test
    public void isExist_fileIsIncorrectFile_returnFalse() {

        testFileForRead = buildIncorrectFile();
        assertFalse(FileUtils.isExist(testFileForRead));
    }

    @Test
    public void isExist_fileIsCorrectFile_returnTrue() {

        assertTrue(FileUtils.isExist(testFileForRead));
    }

    @Test
    public void isExist_fileOrFolderPathIsNotExisted_returnFalse() {

        testFilePath = noSuchFileOrFolder;
        assertFalse(FileUtils.isExist(testFilePath));
    }

    @Test
    public void isExist_filePathIsExisted_returnTrue() {

        assertTrue(FileUtils.isExist(testFilePath));
    }

    @Test
    public void isExist_folderPathIsExisted_returnTrue() {

        assertTrue(FileUtils.isExist(testFolderPathForRead));
    }

    @Test
    public void isDirectory_folderIsIncorrect_returnFalse() {

        testFolderForRead = buildIncorrectFile();
        assertFalse(FileUtils.isDirectory(testFolderForRead));
    }

    @Test
    public void isDirectory_folderIscorrect_returnFalse() {

        assertTrue(FileUtils.isDirectory(testFolderForRead));
    }

    @Test
    public void isDirectory_folderPathIsIncorrect_returnFalse() {

        testFolderPath = noSuchFileOrFolder;
        assertFalse(FileUtils.isDirectory(testFolderPath));
    }

    @Test
    public void isDirectory_folderPathIscorrect_returnFalse() {

        assertTrue(FileUtils.isDirectory(testFolderPath));
    }

    @Test
    public void createDirectory_destinationPathIsIncorrect_returnFalse() {

        boolean isCreateedDirectory = FileUtils.createDirectory(noSuchFileOrFolder);
        assertFalse(isCreateedDirectory);
    }

    @Test
    public void createDirectory_destinationPathIsCorrectAndExisted_returnTrue() {

        boolean isCreateedDirectory = FileUtils.createDirectory(testFolderPathForDelete);
        assertTrue(isCreateedDirectory);
    }

    @Test
    public void createDirectory_destinationPathIsCorrectAndNotExisted_returnTrue() {

        FileUtils.delete(testFolderPathForDelete);
        boolean isCreateedDirectory = FileUtils.createDirectory(testFolderPathForDelete);
        assertTrue(isCreateedDirectory);
    }

    @Test
    public void createDirectory_destinationFileIsIncorrect_returnFalse() {

        boolean isCreateedDirectory = FileUtils.createDirectory(buildIncorrectFile());
        assertFalse(isCreateedDirectory);
    }

    @Test
    public void createDirectory_destinationFileIsCorrectAndExisted_returnTrue() {

        boolean isCreateedDirectory = FileUtils.createDirectory(testFolderForDelete);
        assertTrue(isCreateedDirectory);
    }

    @Test
    public void createDirectory_destinationFileIsCorrectAndNotExisted_returnTrue() {

        FileUtils.delete(testFolderForDelete);
        boolean isCreateedDirectory = FileUtils.createDirectory(testFolderForDelete);
        assertTrue(isCreateedDirectory);
    }

    @Test(expected = FileNotFoundException.class)
    public void getBytes_fileIsIncorrect_throwFileNotFoundException() throws IOException {

        FileUtils.getBytes(buildIncorrectFile());
    }

    @Test
    public void getBytes_fileIsCorrect_returnBytes() throws IOException {

        byte[] bytes = FileUtils.getBytes(testFileForRead);
        assertTrue(bytes.length > 0);
    }

    @Test(expected = FileNotFoundException.class)
    public void getBytes_filePathIsIncorrect_throwFileNotFoundException() throws IOException {

        testFilePath = noSuchFileOrFolder;
        FileUtils.getBytes(testFilePath);
    }

    @Test
    public void getBytes_filePathIsCorrect_returnBytes() throws IOException {

        byte[] bytes = FileUtils.getBytes(testFilePath);
        assertTrue(bytes.length > 0);
    }

    @Test
    public void getAllFilesOfFolder() {

        File[] allFiles = FileUtils.getAllFilesOfFolder(testFolderPathForRead);
        assertEquals(2, allFiles.length);
        println(allFiles);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_inputStreamIsIncorrectAndDestinationFileIsCorrect_throwFileNotFoundException()
            throws IOException {

        testInputStream = new FileInputStream(noSuchFileOrFolder);
        FileUtils.write(testInputStream, testFileForWrite);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_inputStreamIsCorrectAndDestinationFileIsIncorrect_throwFileNotFoundException()
            throws IOException {

        try {
            testFileForWrite = buildIncorrectFile();
            FileUtils.write(testInputStream, testFileForWrite);
        } finally {
            testInputStream.close();
        }
    }

    @Test
    public void write_inputStreamAndDestinationFileBothCorrect_returnTrue() throws IOException {

        try {
            boolean writeSuccess = FileUtils.write(testInputStream, testFileForWrite);
            assertTrue(writeSuccess);
        } finally {
            testInputStream.close();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void write_inputStreamIsIncorrectAndDestinationPathIsCorrect_throwFileNotFoundException()
            throws IOException {

        testInputStream = new FileInputStream(noSuchFileOrFolder);
        FileUtils.write(testInputStream, testWritePath);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_inputStreamIsCorrectAndDestinationPathIsIncorrect_throwFileNotFoundException()
            throws IOException {

        try {
            testWritePath = noSuchFileOrFolder;
            FileUtils.write(testInputStream, testWritePath);
        } finally {
            testInputStream.close();
        }
    }

    @Test
    public void write_inputStreamAndDestinationPathBothCorrect_returnTrue() throws IOException {

        try {
            boolean writeSuccess = FileUtils.write(testInputStream, testWritePath);
            assertTrue(writeSuccess);
        } finally {
            testInputStream.close();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceFileIsIncorrectAndOutputStreamIsCorrect_throwFileNotFoundException()
            throws IOException {

        try {
            testFileForRead = buildIncorrectFile();
            FileUtils.write(testFileForRead, testOutputStream);
        } finally {
            testOutputStream.close();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void write_outputStreamIsIncorrectAndSourceFileIsCorrect_throwFileNotFoundException()
            throws IOException {

        testOutputStream = new FileOutputStream(noSuchFileOrFolder);
        FileUtils.write(testFileForRead, testOutputStream);
    }

    @Test
    public void write_sourceFileAndOutputStreamBothCorrect_returnTrue() throws IOException {

        try {
            boolean writeSuccess = FileUtils.write(testFileForRead, testOutputStream);
            assertTrue(writeSuccess);
        } finally {
            testOutputStream.close();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceFileIsIncorrectAndDestinationFileIsCorrect_throwFileNotFoundException()
            throws IOException {

        testFileForRead = buildIncorrectFile();
        FileUtils.write(testFileForRead, testFileForWrite);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceFileIsCorrectAndDestinationFileIsIncorrect_throwFileNotFoundException()
            throws IOException {

        testFileForWrite = buildIncorrectFile();
        FileUtils.write(testFileForRead, testFileForWrite);
    }

    @Test
    public void write_sourceFileAndDestinationFileBothCorrect_returnTrue() throws IOException {

        boolean writeSuccess = FileUtils.write(testFileForRead, testFileForWrite);
        assertTrue(writeSuccess);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceFileIsIncorrectAndDestinationPathIsCorrect_throwFileNotFoundException()
            throws IOException {

        testFileForRead = buildIncorrectFile();
        FileUtils.write(testFileForRead, testWritePath);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceFileIsCorrectAndDestinationPathIsIncorrect_throwFileNotFoundException()
            throws IOException {

        testWritePath = noSuchFileOrFolder;
        FileUtils.write(testFileForRead, testWritePath);
    }

    @Test
    public void write_sourceFileAndDestinationPathBothCorrect_returnTrue() throws IOException {

        boolean writeSuccess = FileUtils.write(testFileForRead, testWritePath);
        assertTrue(writeSuccess);
    }

    @Test(expected = Exception.class)
    public void write_sourceInputStreamIsIncorrectAndDestinationPathIsCorrect_throwException()
            throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        Object sourceInputStream = new Object();
        FileUtils.write(sourceInputStream, testWritePath);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceInputStreamIsCorrectAndDestinationPathIsIncorrect_throwFileNotFoundException()
            throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        testWritePath = noSuchFileOrFolder;
        Object sourceInputStream = testInputStream;
        FileUtils.write(sourceInputStream, testWritePath);
    }

    @Test
    public void write_parameterBothCorrect_returnTrue() throws IOException, SecurityException,
            IllegalArgumentException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        Object input = testInputStream;
        boolean isWriteSuccess = FileUtils.write(input, testWritePath);
        assertTrue(isWriteSuccess);
        testInputStream.close();
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceBytesIsCorrectAndDestinationFileIsIncorrect_throwFileNotFoundException()
            throws IOException {

        byte[] sourceBytes = new byte[1];
        testFileForWrite = buildIncorrectFile();
        FileUtils.write(sourceBytes, testFileForWrite);
    }

    @Test
    public void write_sourceBytesAndDestinationFileBothCorrect_runSuccess() throws IOException {

        byte[] sourceBytes = FileUtils.getBytes(testFileForRead);
        FileUtils.write(sourceBytes, testFileForWrite);
    }

    @Test(expected = FileNotFoundException.class)
    public void write_sourceBytesIsCorrectAndDestinationPathIsIncorrect_throwFileNotFoundException()
            throws IOException {

        byte[] sourceBytes = new byte[1];
        FileUtils.write(sourceBytes, noSuchFileOrFolder);
    }

    @Test
    public void write_sourceBytesAndDestinationPathBothCorrect_runSuccess() throws IOException {

        byte[] sourceBytes = FileUtils.getBytes(testFileForRead);
        FileUtils.write(sourceBytes, testWritePath);
    }

    @Test
    public void delete_destinationPathIsIncorrect_returnFalse() {

        boolean isDeleted = FileUtils.delete(noSuchFileOrFolder);
        assertFalse(isDeleted);
    }

    @Test
    public void delete_destinationPathIsCorrect_returnTrue() {

        boolean isDeleted = FileUtils.delete(testWritePath);
        assertTrue(isDeleted);
    }

    @Test
    public void delete_destinationFileIsIncorrect_returnFalse() {

        File incorrectFile = buildIncorrectFile();
        boolean isDeleted = FileUtils.delete(incorrectFile);
        assertFalse(isDeleted);
    }

    @Test
    public void delete_destinationFileIsCorrect_returnTrue() {

        boolean isDeleted = FileUtils.delete(testFileForWrite);
        assertTrue(isDeleted);
    }

    @Test
    public void delete_destinationFileIsNotEmptyFolderAndCorrect_returnTrue() throws IOException {

        String testFolderPathForDelete = testPath + "testFolderForDelete/";
        File testFolderForDelete = new File(testFolderPathForDelete);
        testFolderForDelete.mkdirs();
        FileUtils.write(testFileForRead, testFolderPathForDelete + testWriteFileName);
        File testDeleteFolder = new File(testFolderPathForDelete);
        boolean isDeleted = FileUtils.delete(testDeleteFolder);
        assertTrue(isDeleted);
    }

    @Test
    public void delete_destinationFileIsEmptyFolderAndCorrect_returnTrue() throws IOException {

        String testFolderPathForDelete = testPath + "testFolderForDelete/";
        File testFolderForDelete = new File(testFolderPathForDelete);
        testFolderForDelete.mkdirs();
        boolean isDeleted = FileUtils.delete(testFolderForDelete);
        assertTrue(isDeleted);
    }

    @Test
    public void copy_sourceFileIsIncorrectAndDestinationFileBothCorrect_returnFalse()
            throws IOException {

        testFileForRead = buildIncorrectFile();
        FileUtils.delete(testFileForWrite);
        boolean isCopySuccess = FileUtils.copy(testFileForRead, testFileForWrite);
        assertFalse(isCopySuccess);
    }

    @Test(expected = IOException.class)
    public void copy_sourceFileIsCorrectAndDestinationFileIsIncorrect_throwIOException()
            throws IOException {

        testFileForWrite = buildIncorrectFile();
        FileUtils.copy(testFileForRead, testFileForWrite);
    }

    @Test
    public void copy_sourceFileAndDestinationFileBothCorrect_returnTrue() throws IOException {

        FileUtils.delete(testFileForWrite);
        boolean isCopySuccess = FileUtils.copy(testFileForRead, testFileForWrite);
        boolean destinationFileIsExist = FileUtils.isExist(testFileForWrite);
        assertEquals(isCopySuccess, destinationFileIsExist);
        FileUtils.delete(testFileForWrite);
    }

    @Test
    public void copy_sourceFolderAndDestinationFolderBothCorrect_returnTrue() throws IOException {

        FileUtils.delete(testFolderForDelete);
        boolean isCopySuccess = FileUtils.copy(testFolderForRead, testFolderForDelete);
        boolean destinationFileIsExist = FileUtils.isExist(testFolderForDelete);
        assertEquals(isCopySuccess, destinationFileIsExist);
        FileUtils.delete(testFolderForDelete);
    }

    @Test
    public void copy_sourceFileAndDestinationFolderBothCorrect_returnTrue() throws IOException {

        FileUtils.delete(testFolderForDelete);
        boolean isCopySuccess = FileUtils.copy(testFileForRead, testFolderForDelete);
        boolean destinationFileIsExist = FileUtils.isExist(testFolderForDelete);
        assertEquals(isCopySuccess, destinationFileIsExist);
        FileUtils.delete(testFolderForDelete);
    }

    @Test
    public void copy_sourceFolderAndDestinationFileBothCorrect_returnTrue() throws IOException {

        FileUtils.delete(testFileForWrite);
        boolean isCopySuccess = FileUtils.copy(testFolderForRead, testFileForWrite);
        boolean destinationFileIsExist = FileUtils.isExist(testFileForWrite);
        assertEquals(isCopySuccess, destinationFileIsExist);
        FileUtils.delete(testFileForWrite);
    }

    private File buildIncorrectFile() {

        return new File(noSuchFileOrFolder);
    }
}
