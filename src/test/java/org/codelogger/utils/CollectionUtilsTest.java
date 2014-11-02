package org.codelogger.utils;

/**
 * Already Passed The Test.
 * 
 * @author DengDefei
 */
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.codelogger.utils.PrintUtils.println;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.codelogger.utils.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

public class CollectionUtilsTest {

    private Set<String> set;

    private List<String> list;

    @Before
    public void init() {

        set = new TreeSet<String>();
        list = new ArrayList<String>();
    }

    @Test
    public void isCollection() {

        assertTrue(CollectionUtils.isCollection(set));
        assertTrue(CollectionUtils.isCollection(list));
        String[] array = {};
        assertFalse(CollectionUtils.isCollection(array));
    }

    @Test
    public void isEmpty_setIsNull_exceptTrue() {

        set = null;
        assertTrue(CollectionUtils.isEmpty(set));
    }

    @Test
    public void isEmpty_emptySet_exceptTrue() {

        assertTrue(CollectionUtils.isEmpty(set));
    }

    @Test
    public void isEmpty_notEmptySet_exceptFalse() {

        set.add("a");
        assertFalse(CollectionUtils.isEmpty(set));
    }

    @Test
    public void isNotEmpty_setIsNull_exceptFalse() {

        set = null;
        assertFalse(CollectionUtils.isNotEmpty(set));
    }

    @Test
    public void isNotEmpty_emptySet_exceptFalse() {

        assertFalse(CollectionUtils.isNotEmpty(set));
    }

    @Test
    public void isNotEmpty_notEmptySet_exceptTrue() {

        set.add("a");
        assertTrue(CollectionUtils.isNotEmpty(set));
    }

    @Test
    public void isEmpty_listIsNull_exceptTrue() {

        list = null;
        assertTrue(CollectionUtils.isEmpty(list));
    }

    @Test
    public void isEmpty_emptyList_exceptTrue() {

        assertTrue(CollectionUtils.isEmpty(list));
    }

    @Test
    public void isEmpty_notEmptyList_exceptFalse() {

        buildList(1);
        assertFalse(CollectionUtils.isEmpty(list));
    }

    @Test
    public void isNotEmpty_listIsNull_exceptFalse() {

        list = null;
        assertFalse(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void isNotEmpty_emptyList_exceptFalse() {

        assertFalse(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void isNotEmpty_notEmptyList_exceptTrue() {

        buildList(1);
        assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void getFirstOrNull_listIsNull_returnNull() {

        list = null;
        assertEquals(null, CollectionUtils.getFirstOrNull(list));
    }

    @Test
    public void getFirstOrNull_emptyList_returnNull() {

        assertEquals(null, CollectionUtils.getFirstOrNull(list));
    }

    @Test
    public void getFirstOrNull_notEmptyList_returnFirstValue() {

        buildList(2);
        assertEquals(list.get(0), CollectionUtils.getFirstOrNull(list));
    }

    @Test
    public void getFirstNotNullValue_listIsNull_returnNull() {

        list = null;
        assertEquals(null, CollectionUtils.getFirstNotNullValue(list));
    }

    @Test
    public void getFirstNotNullValue_emptyList_returnNull() {

        assertEquals(null, CollectionUtils.getFirstNotNullValue(list));
    }

    @Test
    public void getFirstNotNullValue_notEmptyList_returnFirstValue() {

        buildList(2);
        assertEquals(list.get(0), CollectionUtils.getFirstNotNullValue(list));
    }

    @Test
    public void setToList_empty() {

        List<String> list2 = CollectionUtils.toList(set);
        assertEquals(0, list2.size());
    }

    @Test
    public void setToList_notEmpty() {

        set.add("a");
        set.add("b");
        set.add("c");
        List<String> list2 = CollectionUtils.toList(set);
        assertEquals(3, list2.size());
        assertTrue(list2.contains("a"));
    }

    @Test
    public void listToSet_empty() {

        assertEquals(0, list.size());
        Set<String> set2 = CollectionUtils.toSet(list);
        assertEquals(0, set2.size());
    }

    @Test
    public void listToSet_notEmpty() {

        list.add("a");
        list.add("b");
        list.add(null);
        list.add("c");
        list.add("c");
        list.add(null);
        list.add("a");
        assertEquals(7, list.size());
        Set<String> set2 = CollectionUtils.toSet(list);
        assertEquals(4, set2.size());
        assertTrue(set2.contains("a"));
    }

    @Test
    public void listToArray_listIsNull_returnNull() {

        list = null;
        assertEquals(null, CollectionUtils.toArray(list));
    }

    @Test
    public void listToArray_listIsEmpty_returnNull() {

        assertEquals(null, CollectionUtils.toArray(list));
    }

    @Test
    public void listToArray_listIsNotEmpty_returnCorrectValue() {

        int listSize = 2;
        buildList(listSize);
        String[] listToArray = CollectionUtils.toArray(list);
        assertEquals(listSize, listToArray.length);
        assertEquals(list.get(0), listToArray[0]);
    }

    @Test
    public void disorder() {

        buildList(100);
        println(list);
        List<String> disorder = CollectionUtils.disorder(list);
        println(disorder);
        boolean isSame = true;
        for (int i = 0; i < 100; i++) {
            boolean same = list.get(i).equals(disorder.get(i));
            if (!same) {
                isSame = false;
                break;
            }
        }
        assertFalse(isSame);
    }

    @Test
    public void getLastElementForList() {

        String lastElement = CollectionUtils.getLastElement(list);
        assertEquals(null, lastElement);
        buildList(5);
        lastElement = CollectionUtils.getLastElement(list);
        assertEquals("4", lastElement);
    }

    @Test
    public void getLastElementForSet() {

        buildSet(5);
        String lastElement = CollectionUtils.getLastElement(set);
        assertEquals("4", lastElement);
    }

    @Test
    public void getCount() {

        int count = CollectionUtils.getCount(list);
        assertEquals(0, count);

        list = new ArrayList<String>();
        count = CollectionUtils.getCount(list);
        assertEquals(0, count);

        list.add("A");
        count = CollectionUtils.getCount(list);
        assertEquals(1, count);
    }

    private void buildList(final int listSize) {

        for (int i = 0; i < listSize; i++) {
            list.add(i + "");
        }
    }

    private void buildSet(final int size) {

        for (int i = 0; i < size; i++) {
            set.add(i + "");
        }
    }
}
