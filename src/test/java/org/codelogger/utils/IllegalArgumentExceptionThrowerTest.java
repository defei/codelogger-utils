package org.codelogger.utils;

/**
 * Already Passed The Test.
 * 
 * @author DengDefei
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class IllegalArgumentExceptionThrowerTest {

    private final IllegalArgumentExceptionThrower iae = IllegalArgumentExceptionThrower
            .getInstance();

    private Object testObject;

    private Collection<String> collection;

    private Map<String, String> map;

    private String[] array;

    @Test
    public void throwIfNull_conditionIsFalse_runPassed() {

        iae.throwIfTrue(false, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfNull_conditionIsTrue_IllegalArgumentException() {

        iae.throwIfTrue(true, null);
    }

    @Test
    public void throwIfNull_conditionIsTrue_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message!";
        try {
            iae.throwIfTrue(true, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_conditionIsTrueAndMessageIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = null;
        try {
            iae.throwIfTrue(true, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_conditionIsTrueAndHaveMessageAndHaveMessageParameters_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = { "Id", 1l };
        String exceptedMessage = "This is message: Id == 1";
        try {
            iae.throwIfTrue(true, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptedMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_conditionIsTrueAndHaveMessageAndMessageParametersIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = null;
        try {
            iae.throwIfTrue(true, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateObjectIsNotNull_runPassed() {

        testObject = new Object();
        iae.throwIfNull(testObject, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfNull_validateObjectIsNull_IllegalArgumentException() {

        iae.throwIfNull(testObject, null);
    }

    @Test
    public void throwIfNull_validateObjectIsNullAndHaveMessage_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message!";
        try {
            iae.throwIfNull(testObject, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateObjectIsNullAndMessageIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = null;
        try {
            iae.throwIfNull(testObject, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateObjectIsNullAndHaveMessageAndHaveMessageParameters_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = { "Id", 1l };
        String exceptedMessage = "This is message: Id == 1";
        try {
            iae.throwIfNull(testObject, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptedMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateObjectIsNullAndHaveMessageAndMessageParametersIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = null;
        try {
            iae.throwIfNull(testObject, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfEmpty_notEmptyList_runPassed() {

        collection = new ArrayList<String>();
        collection.add("a");
        iae.throwIfEmptyOrNull(collection, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfEmpty_emptyList_IllegalArgumentException() {

        iae.throwIfEmptyOrNull(collection, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfEmpty_emptyList_runPassed() {

        collection = new ArrayList<String>();
        iae.throwIfEmptyOrNull(collection, null);
    }

    @Test
    public void throwIfNull_validateCollectionIsEmptyOrNullAndHaveMessage_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message!";
        try {
            iae.throwIfEmptyOrNull(collection, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateCollectionIsEmptyOrNullAndHaveMessageAndHaveMessageParameters_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = { "Id", 1l };
        String exceptedMessage = "This is message: Id == 1";
        try {
            iae.throwIfEmptyOrNull(collection, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptedMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateCollectionIsEmptyOrNullAndHaveMessageAndMessageParametersIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = null;
        try {
            iae.throwIfEmptyOrNull(collection, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfEmpty_notEmptyMap_runPassed() {

        map = new HashMap<String, String>();
        map.put("a", "a");
        iae.throwIfEmptyOrNull(map, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfEmpty_emptyMap_IllegalArgumentException() {

        iae.throwIfEmptyOrNull(map, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfEmpty_emptyMap_runPassed() {

        collection = new ArrayList<String>();
        iae.throwIfEmptyOrNull(map, null);
    }

    @Test
    public void throwIfNull_validateMapIsEmptyOrNullAndHaveMessage_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message!";
        try {
            iae.throwIfEmptyOrNull(collection, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateMapIsEmptyOrNullAndHaveMessageAndHaveMessageParameters_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = { "Id", 1l };
        String exceptedMessage = "This is message: Id == 1";
        try {
            iae.throwIfEmptyOrNull(collection, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptedMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateMapIsEmptyOrNullAndHaveMessageAndMessageParametersIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = null;
        try {
            iae.throwIfEmptyOrNull(collection, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfEmpty_notEmptyArray_runPassed() {

        array = new String[1];
        array[0] = "a";
        iae.throwIfEmptyOrNull(array, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfEmpty_emptyArray_IllegalArgumentException() {

        iae.throwIfEmptyOrNull(array, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfEmpty_emptyArray_runPassed() {

        array = new String[0];
        iae.throwIfEmptyOrNull(array, null);
    }

    @Test
    public void throwIfNull_validateArrayIsEmptyOrNullAndHaveMessage_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message!";
        try {
            iae.throwIfEmptyOrNull(array, exceptionMessage);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateArrayIsEmptyOrNullAndHaveMessageAndHaveMessageParameters_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = { "Id", 1l };
        String exceptedMessage = "This is message: Id == 1";
        try {
            iae.throwIfEmptyOrNull(array, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptedMessage, e.getMessage());
        }
    }

    @Test
    public void throwIfNull_validateArrayIsEmptyOrNullAndHaveMessageAndMessageParametersIsNull_IllegalArgumentExceptionAndCouldGetCorrectExceptionMessage() {

        String exceptionMessage = "This is message: %s == %s";
        Object[] parameters = null;
        try {
            iae.throwIfEmptyOrNull(array, exceptionMessage, parameters);
        } catch (Exception e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

}
