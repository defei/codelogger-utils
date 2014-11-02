package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.codelogger.utils.PropertiesUtils;
import org.codelogger.utils.exceptions.PropertiesException;

import org.junit.Test;

public class PropertiesUtilsTest {

    @Test
    public void getPropertiesFromCache_hasSuffix() {

        String expectedName = "sexafei";
        Properties properties = PropertiesUtils
                .getPropertiesFromCache("FileUtilsTest/test.properties");
        assertNotNull(properties);
        println(properties.get("name"));
        assertEquals(expectedName, properties.get("name"));
    }

    @Test
    public void getPropertiesFromCache_noSuffix() {

        String expectedName = "sexafei";
        Properties properties = PropertiesUtils.getPropertiesFromCache("FileUtilsTest/test");
        assertNotNull(properties);
        println(properties.get("name"));
        assertEquals(expectedName, properties.get("name"));
    }

    @Test
    public void hasUsedCache() {

        PropertiesUtils.getPropertiesFromCache("FileUtilsTest/test.properties");
        PropertiesUtils.getPropertiesFromCache("FileUtilsTest/test");
    }

    @Test(expected = PropertiesException.class)
    public void getPropertiesFromCache_noSuchProperties_throwPropertiesException() {

        PropertiesUtils.getPropertiesFromCache("test.properties");
    }

    @Test
    public void getReloadPropertiesInToCache() {

        PropertiesUtils.getReloadPropertiesInToCache("FileUtilsTest/test.properties");
        PropertiesUtils.getReloadPropertiesInToCache("FileUtilsTest/test");
    }

    @Test(expected = PropertiesException.class)
    public void getReloadPropertiesInToCache_noSuchProperties_throwPropertiesException() {

        PropertiesUtils.getReloadPropertiesInToCache("test.properties");
    }

}
