package org.codelogger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MapUtilsTest {

    private Map<String, String> map;

    @Before
    public void init() {

        map = new HashMap<String, String>();
    }

    @Test
    public void isEmpty_mapIsNull_exceptFalse() {

        map = null;
        assertTrue(MapUtils.isEmpty(map));
    }

    @Test
    public void isEmpty_emptyMap_exceptTrue() {

        assertTrue(MapUtils.isEmpty(map));
    }

    @Test
    public void isEmpty_notEmptyMap_exceptFalse() {

        map.put("a", "a");
        assertFalse(MapUtils.isEmpty(map));
    }

    @Test
    public void isNotEmpty_mapIsNull_exceptFalse() {

        map = null;
        assertFalse(MapUtils.isNotEmpty(map));
    }

    @Test
    public void isNotEmpty_emptyMap_exceptFalse() {

        assertFalse(MapUtils.isNotEmpty(map));
    }

    @Test
    public void isNotEmpty_notEmptyMap_exceptTrue() {

        map.put("a", "a");
        assertTrue(MapUtils.isNotEmpty(map));
    }

    @Test
    public void getMapValuesOrderByKey_allValueNotNull() {

        Map<String, Long> valuesMap = Maps.newHashMap();
        valuesMap.put("a", 1L);
        valuesMap.put("b", 2L);
        valuesMap.put("c", 3L);
        List<String> keys = Lists.newArrayList("a", "b", "c");
        Long defaultValue = 0L;
        Long[] valuesOrderByKey = MapUtils.getMapValuesOrderByKey(valuesMap, keys, defaultValue);
        for (Long i = 1L; i < 4; i++) {
            assertEquals(i, valuesOrderByKey[i.intValue() - 1]);
        }
    }

    @Test
    public void getMapValuesOrderByKey_defaultValueIsNull() {

        Map<String, Long> valuesMap = Maps.newHashMap();
        valuesMap.put("a", 1L);
        valuesMap.put("b", 2L);
        valuesMap.put("c", 3L);
        List<String> keys = Lists.newArrayList("a", "b", "c");
        Long defaultValue = null;
        Long[] valuesOrderByKey = MapUtils.getMapValuesOrderByKey(valuesMap, keys, defaultValue);
        for (Long i = 1L; i < 4; i++) {
            assertEquals(i, valuesOrderByKey[i.intValue() - 1]);
        }
    }

    @Test
    public void getMapValuesOrderByKey_mapValueNotAllNull() {

        Map<String, Long> valuesMap = Maps.newHashMap();
        valuesMap.put("a", null);
        valuesMap.put("b", 2L);
        valuesMap.put("c", 3L);
        List<String> keys = Lists.newArrayList("a", "b", "c");
        Long defaultValue = null;
        Long[] valuesOrderByKey = MapUtils.getMapValuesOrderByKey(valuesMap, keys, defaultValue);
        assertEquals(null, valuesOrderByKey[0]);
        for (Long i = 2L; i < 4; i++) {
            assertEquals(i, valuesOrderByKey[i.intValue() - 1]);
        }
    }

    @Test
    public void getMapValuesOrderByKey_mapValueAllNullAndDefaultNotNull() {

        Map<String, Long> valuesMap = Maps.newHashMap();
        valuesMap.put("a", null);
        valuesMap.put("b", null);
        valuesMap.put("c", null);
        List<String> keys = Lists.newArrayList("a", "b", "c");
        Long defaultValue = 0L;
        Long[] valuesOrderByKey = MapUtils.getMapValuesOrderByKey(valuesMap, keys, defaultValue);
        for (Long i = 1L; i < 4; i++) {
            assertEquals(defaultValue, valuesOrderByKey[i.intValue() - 1]);
        }
    }

    @Test
    public void getMapValuesOrderByKey_mapValueAllNullAndDefaultIsNull() {

        Map<String, Long> valuesMap = Maps.newHashMap();
        valuesMap.put("a", null);
        valuesMap.put("b", null);
        valuesMap.put("c", null);
        List<String> keys = Lists.newArrayList("a", "b", "c");
        Long defaultValue = null;
        Long[] valuesOrderByKey = MapUtils.getMapValuesOrderByKey(valuesMap, keys, defaultValue);
        assertNull(valuesOrderByKey);
    }
}
