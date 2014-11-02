package org.codelogger.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.codelogger.utils.exceptions.PropertiesException;

public class PropertiesUtils {

    private static Map<String, Properties> propertiesCache = new Hashtable<String, Properties>();

    private PropertiesUtils() {

    }

    /**
     * Returns properties by given path.<br>
     * <p>
     * e.g:<br>
     * "testFolder/propertiesName" or "testFolder/propertiesName.properties"
     * </p>
     * 
     * @param propertiesPath
     *            the properties path.
     * @return properties by given path.
     * @throws PropertiesException
     */
    public static Properties getProperties(final String propertiesPath) throws PropertiesException {

        Properties properties;
        String propertiesFilePath = getPropertiesFilePath(propertiesPath);
        if (propertiesFilePath == null) {
            throw new PropertiesException(String.format(
                    "Loading '%s' properties failed, no such properties.", propertiesPath));
        }
        FileInputStream fileInputStream = null;
        try {
            properties = new Properties();
            fileInputStream = new FileInputStream(propertiesFilePath);
            properties.load(fileInputStream);
            fileInputStream.close();
            return properties;
        } catch (Exception e) {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            throw new PropertiesException(String.format("Loading '%s' properties failed.",
                    propertiesPath), e);
        }
    }

    /**
     * Returns properties by given path.<br>
     * If this properties already been put in the cache, we will returns this
     * properties from cache.<br>
     * <p>
     * e.g:<br>
     * "testFolder/propertiesName" or "testFolder/propertiesName.properties"
     * </p>
     * 
     * @param propertiesPath
     *            the properties path.
     * @return properties by given path.
     * @throws PropertiesException
     */
    public static Properties getPropertiesFromCache(final String propertiesPath)
            throws PropertiesException {

        Properties properties;
        properties = propertiesCache.get(propertiesPath);
        if (properties != null) {
            return properties;
        }
        String propertiesFilePath = getPropertiesFilePath(propertiesPath);
        if (propertiesFilePath == null) {
            throw new PropertiesException(String.format("Loading '%s' properties failed.",
                    propertiesPath));
        }
        properties = propertiesCache.get(propertiesFilePath);
        if (properties != null) {
            return properties;
        }
        properties = getProperties(propertiesFilePath);
        propertiesCache.put(propertiesFilePath, properties);
        return properties;
    }

    /**
     * Returns properties by given path, and put this properties into cache, you
     * can called getProperties method to get this properties from cache next.<br>
     * <p>
     * e.g:<br>
     * "testFolder/propertiesName" or "testFolder/propertiesName.properties"
     * </p>
     * 
     * @param propertiesPath
     *            the properties path.
     * @return properties by given path.
     * @throws PropertiesException
     */
    public static Properties getReloadPropertiesInToCache(final String propertiesPath)
            throws PropertiesException {

        String propertiesFilePath = getPropertiesFilePath(propertiesPath);
        Properties properties = getProperties(propertiesFilePath);
        propertiesCache.put(propertiesFilePath, properties);
        return properties;
    }

    private static String getPropertiesFilePath(final String propertiesPath) {

        if (propertiesPath == null) {
            return null;
        }
        String path;
        if (StringUtils.endsWithIgnoreCase(propertiesPath, ".properties")) {
            path = propertiesPath;
        } else {
            path = String.format("%s.properties", propertiesPath);
        }

        boolean exist = FileUtils.isExist(path);
        if (exist) {
            return path;
        }
        return null;
    }
}
