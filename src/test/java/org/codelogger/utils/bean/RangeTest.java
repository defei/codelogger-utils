package org.codelogger.utils.bean;

import static com.google.common.collect.Sets.newTreeSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.codelogger.utils.DateUtils;
import org.codelogger.utils.PrintUtils;
import org.codelogger.utils.beans.Range;
import org.junit.Test;

public class RangeTest {

    Range<Date> range;

    @Test
    public void buildRange_bothNull() {

        try {
            Date date = null;
            range = new Range<Date>(date, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date date = new Date();
            range = new Range<Date>(date, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date date = new Date();
            range = new Range<Date>(null, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buildRange_incorrectValue() {

        try {
            Date toDate = new Date();
            Date fromDate = DateUtils.getDateOfDaysBack(6, toDate);
            range = new Range<Date>(toDate, fromDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toString_Date() {

        Date date = new Date();
        range = new Range<Date>(date, date);
        System.out.println(range);
    }

    @Test
    public void toString_Long() {

        Range<Long> range2 = new Range<Long>(1L, 21L);
        System.out.println(range2);
    }

    @Test
    public void toString_String() {

        Range<String> range2 = new Range<String>("abcd", "acbd");
        System.out.println(range2);
    }

    @Test
    public void includes_Date() {

        Date toDate = new Date();
        Date middleDate = DateUtils.getDateOfDaysBack(3, toDate);
        Date fromDate = DateUtils.getDateOfDaysBack(6, toDate);
        Date beforeRangeDate = DateUtils.getDateOfDaysBack(8, toDate);
        Date afterRangeDate = DateUtils.getDateOfDaysBack(-8, toDate);
        range = new Range<Date>(fromDate, toDate);
        System.out.println(range);
        assertTrue(range.includes(middleDate));
        assertFalse(range.includes(beforeRangeDate));
        assertFalse(range.includes(afterRangeDate));
    }

    @Test
    public void equalTest() {

        Set<Integer> set = new HashSet<Integer>();
        Date date = new Date();
        Range<String> range1 = new Range<String>("A", "C");
        Range<String> range2 = new Range<String>("A", "C");
        Range<String> range3 = new Range<String>("a", "c");
        Range<Date> range6 = new Range<Date>(date, date);
        set.add(range1.hashCode());
        set.add(range2.hashCode());
        set.add(range3.hashCode());
        assertTrue(range1.equals(range2));
        assertTrue(range2.equals(range1));
        assertFalse(range1.equals(date));
        assertFalse(range1.equals(range3));
        assertFalse(range3.equals(range1));
        assertFalse(range1.equals(null));
        assertFalse(range1.equals(range6));
        assertFalse(range6.equals(range1));
        assertEquals(2, set.size());
        PrintUtils.println(set);
    }

    @Test
    public void compareTo() {

        Long[] expectLowerBoundResult = { 1L, 1L, 2L, 3L };
        Long[] expectUpperBoundResult = { 4L, 7L, 7L, 8L };
        Set<Range<Long>> rangeSet = newTreeSet();
        rangeSet.add(Range.getInstance(3L, 8L));
        rangeSet.add(Range.getInstance(1L, 7L));
        rangeSet.add(Range.getInstance(2L, 7L));
        rangeSet.add(Range.getInstance(1L, 4L));
        int index = 0;
        for (Range<Long> range : rangeSet) {
            Long lowerBound = range.getLowerBound();
            Long upperBound = range.getUpperBound();
            assertEquals(expectLowerBoundResult[index], lowerBound);
            assertEquals(expectUpperBoundResult[index++], upperBound);
        }
    }
}
