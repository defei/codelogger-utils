package org.codelogger.utils;

import static org.junit.Assert.assertEquals;

import org.codelogger.utils.ValueUtils;
import org.junit.Test;

public class ValueUtilsTest {

    @Test
    public void getValue() {

        Long testLong = null;
        Long expectedLongValue = 0L;
        Long longValueOrNull = ValueUtils.getValue(testLong);
        assertEquals(expectedLongValue, longValueOrNull);
        testLong = 21L;
        longValueOrNull = ValueUtils.getValue(testLong);
        assertEquals(testLong, longValueOrNull);

        Integer testInteger = null;
        Integer expectedIntegerValue = 0;
        Integer intValueOrNull = ValueUtils.getValue(testInteger);
        assertEquals(expectedIntegerValue, intValueOrNull);
        testInteger = 21;
        intValueOrNull = ValueUtils.getValue(testInteger);
        assertEquals(testInteger, intValueOrNull);

        Double testDouble = null;
        Double expectedDoubleValue = 0D;
        Double doubleValueOrNull = ValueUtils.getValue(testDouble);
        assertEquals(expectedDoubleValue, doubleValueOrNull);
        testDouble = 21D;
        doubleValueOrNull = ValueUtils.getValue(testDouble);
        assertEquals(testDouble, doubleValueOrNull);

        Float testFloat = null;
        Float expectedFloatValue = 0F;
        Float floatValueOrNull = ValueUtils.getValue(testFloat);
        assertEquals(expectedFloatValue, floatValueOrNull);
        testFloat = 21F;
        floatValueOrNull = ValueUtils.getValue(testFloat);
        assertEquals(testFloat, floatValueOrNull);

        Number testNumber = null;
        Number expectedNumberValue = 0;
        Number numberValueOrNull = ValueUtils.getValue(testNumber);
        assertEquals(expectedNumberValue, numberValueOrNull);
        testNumber = 21F;
        numberValueOrNull = ValueUtils.getValue(testNumber);
        assertEquals(testNumber, numberValueOrNull);

        Boolean testBoolean = null;
        Boolean expectedBooleanValue = false;
        Boolean actualValue = ValueUtils.getValue(testBoolean);
        assertEquals(expectedBooleanValue, actualValue);
        testBoolean = false;
        actualValue = ValueUtils.getValue(testBoolean);
        assertEquals(expectedBooleanValue, actualValue);
        testBoolean = true;
        expectedBooleanValue = true;
        actualValue = ValueUtils.getValue(testBoolean);
        assertEquals(expectedBooleanValue, actualValue);

    }

    @Test
    public void toString_long() {

        String expected = "321396";
        System.out.println(ValueUtils.toString(321396, 5));
        String actual = ValueUtils.toString(321396, 5);
        assertEquals(expected, actual);

        expected = "000000000321396";
        System.out.println(ValueUtils.toString(321396, 15));
        actual = ValueUtils.toString(321396, 15);
        assertEquals(expected, actual);
    }

}
