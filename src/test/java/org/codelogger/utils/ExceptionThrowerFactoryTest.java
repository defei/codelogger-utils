package org.codelogger.utils;

/**
 * Already Passed The Test.
 * 
 * @author DengDefei
 */

import static org.junit.Assert.assertNotNull;

import org.codelogger.utils.exceptions.InvalidRangeException;
import org.junit.Test;

public class ExceptionThrowerFactoryTest {

    Class<RuntimeException> classType;

    @Test
    public void instance_parameterIsCorrectExceptionClass_returnExceptionThrower() {

        ExceptionThrower instance = ExceptionThrowerFactory
                .instance(IllegalArgumentException.class);
        assertNotNull(instance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instance_parameterIsIncorrectExceptionClass_returnExceptionThrower() {

        ExceptionThrowerFactory.instance(InvalidRangeException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instance_parameterIsNull_returnExceptionThrower() {

        ExceptionThrowerFactory.instance(classType);
    }
}
