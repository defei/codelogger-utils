package org.codelogger.utils;

import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codelogger.utils.PrintUtils;
import org.junit.Test;

public class PrintUtilsTest {

    byte byteValue = 'b';

    byte[] byteArray = { 'a', 'b', 'c', 'd', 'e' };

    short shortValue = 's';

    short[] shortArray = { 'a', 'b', 'c', 'd', 'e' };

    char charValue = 'c';

    char[] charArray = { 'a', 'b', 'c', 'd', 'e' };

    int intValue = 121;

    int[] intArray = { 1, 2, 3, 4, 5 };

    float floatValue = 1.21F;

    float[] floatArray = { 1.21F, 1.22F, 1.23F, 1.24F, 1.25F };

    long longValue = 121L;

    long[] longArray = { 1L, 2L, 3L, 4L, 5L };

    double doubleValue = 1.21D;

    double[] doubleArray = { 1.21D, 1.22D, 1.23D, 1.24D, 1.25D };

    boolean booleanValue = false;

    boolean[] booleanArray = { true, false, true, true, false };

    Object objectValue = new Object();

    Object[] objectArray = new Object[5];

    String stringValue = "Just for test!";

    String[] stringArray = { "a", "b", "c", "d", "e" };

    String prefix = "This is prefix: ";

    String suffix = " This is suffix!";

    @Test
    public void print_onlySingleValue() {

        PrintUtils.print(byteValue);
        PrintUtils.print(shortValue);
        PrintUtils.print(charValue);
        PrintUtils.print(intValue);
        PrintUtils.print(floatValue);
        PrintUtils.print(longValue);
        PrintUtils.print(doubleValue);
        PrintUtils.print(booleanValue);
        PrintUtils.print(objectValue);
        PrintUtils.print(stringValue);
    }

    @Test
    public void println_onlySingleValue() {

        PrintUtils.println(byteValue);
        PrintUtils.println(shortValue);
        PrintUtils.println(charValue);
        PrintUtils.println(intValue);
        PrintUtils.println(floatValue);
        PrintUtils.println(longValue);
        PrintUtils.println(doubleValue);
        PrintUtils.println(booleanValue);
    }

    @Test
    public void print_onlySingleArrayValue() {

        PrintUtils.print(byteArray);
        PrintUtils.print(shortArray);
        PrintUtils.print(charArray);
        PrintUtils.print(intArray);
        PrintUtils.print(floatArray);
        PrintUtils.print(longArray);
        PrintUtils.print(doubleArray);
        PrintUtils.print(booleanArray);
        PrintUtils.print(objectArray);
        PrintUtils.print(stringArray);
    }

    @Test
    public void println_onlySingleArrayValue() {

        PrintUtils.println(byteArray);
        PrintUtils.println(shortArray);
        PrintUtils.println(charArray);
        PrintUtils.println(intArray);
        PrintUtils.println(floatArray);
        PrintUtils.println(longArray);
        PrintUtils.println(doubleArray);
        PrintUtils.println(booleanArray);
        PrintUtils.println(objectArray);
        PrintUtils.println(stringArray);
    }

    private Map<String, String> buildStringMap() {

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("a", "a");
        stringMap.put("b", "b");
        stringMap.put("c", "c");
        return stringMap;
    }

    @Test
    public void print_map1() {

        Map<String, Map<String, String>> maps = new HashMap<String, Map<String, String>>();
        Map<String, String> stringMap1 = buildStringMap();
        Map<String, String> stringMap2 = buildStringMap();
        Map<String, String> stringMap3 = buildStringMap();
        maps.put("a", stringMap1);
        maps.put("b", stringMap2);
        maps.put("c", stringMap3);
        PrintUtils.print(maps);
        PrintUtils.println(maps);
    }

    @Test
    public void print_List() {

        List<String> stringList = buildStringList();
        PrintUtils.print(stringList);
        PrintUtils.println(stringList);
    }

    private List<String> buildStringList() {

        List<String> stringList = new ArrayList<String>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        return stringList;
    }

    @Test
    public void print_List1() {

        List<List<String>> lists = new ArrayList<List<String>>();
        lists.add(buildStringList());
        lists.add(buildStringList());
        lists.add(buildStringList());
        PrintUtils.print(lists);
        PrintUtils.println(lists);
    }

    @Test
    public void print_MapAndList() {

        Map<String, List<String>> maps = new HashMap<String, List<String>>();
        List<String> lists1 = buildStringList();
        maps.put("a", lists1);
        List<String> lists2 = buildStringList();
        maps.put("b", lists2);
        List<String> lists3 = buildStringList();
        maps.put("c", lists3);
        PrintUtils.print(maps);
        PrintUtils.println(maps);
    }

    @Test
    public void format() {

        Map<String, Object> map = newHashMap();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", booleanArray);

        PrintUtils.print("Test for String:%s", "String");
        PrintUtils.println("Test for String:%s", "String");
        PrintUtils.print(map);
        PrintUtils.println(map);
        PrintUtils.print("Test for Map:%s", map);
        PrintUtils.println("Test for Map:%s", map);
        PrintUtils.print("Test for intArray:%s", intArray);
        PrintUtils.println("Test for intArray:%s", intArray);
        PrintUtils.print("Test for three args(String,Map,Array):%s,%s,%s", "String", map, intArray);
        PrintUtils.print("Test for three args(String,Map,Array):%s,%s,%s", "String", map, intArray);
        PrintUtils.println("Test for three args(String,Map,Array):%s,%s,%s", "String", map,
                intArray);
    }
}
