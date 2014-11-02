package org.codelogger.utils.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.codelogger.utils.PrintUtils;

import org.codelogger.utils.beans.StorageComponent;
import org.junit.Test;

public class StorageComponentTest {

    @Test
    public void instancation() {

        Integer expectedValue = 2;
        StorageComponent<Long, Integer> storageComponent = new StorageComponent<Long, Integer>(
                expectedValue, 3L, 2L, 3L, 1L);
        PrintUtils.println(storageComponent);
        assertEquals(expectedValue, storageComponent.get(3L, 2L, 3L, 1L));

        StorageComponent<Long, Integer> storageComponent1 = new StorageComponent<Long, Integer>();
        storageComponent1.put(expectedValue, 3L, 2L, 3L, 1L);
        PrintUtils.println(storageComponent1);
        assertEquals(expectedValue, storageComponent1.get(3L, 2L, 3L, 1L));

        assertNotSame(storageComponent, storageComponent1);

        expectedValue = 21;
        storageComponent1.put(expectedValue, 3L, 2L, 3L, 5L, 2L);
        PrintUtils.println(storageComponent1);
        Integer actualValue = storageComponent1.get(3L, 2L, 3L, 5L, 2L);
        assertEquals(expectedValue, actualValue);

        actualValue = storageComponent1.get(3L, 2L, 3L, 5L, 3L);
        assertEquals(null, actualValue);

        expectedValue = 28;
        storageComponent1.put(expectedValue, 3L);
        actualValue = storageComponent1.get(3L);
        assertEquals(expectedValue, actualValue);

    }
}
