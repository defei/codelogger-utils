package org.codelogger.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.codelogger.utils.beans.Range;
import org.codelogger.utils.exceptions.InvalidRangeException;
import org.codelogger.utils.lang.Period;
import org.junit.Test;

public class RangeUtilsTest {

    /**
     * "2012-12-21"
     */
    private final String simpleTestDate = "2012-12-21";

    private final String simpleTestDateFormat = "yyyy-MM-dd";

    private Date testDate;

    @Test
    public void getDateRanges_day() {

        testDate = buildDate(simpleTestDate, simpleTestDateFormat);
        Date fromDate = buildDate("2012-12-01", simpleTestDateFormat);
        List<Range<Date>> dateRanges = RangeUtils.getDateRanges(fromDate, testDate, Period.DAY);
        assertEquals(21, dateRanges.size());
        PrintUtils.println(dateRanges);
        try {
            RangeUtils.getDateRanges(testDate, fromDate, Period.DAY);
        } catch (Exception e) {
            assertEquals(InvalidRangeException.class, e.getClass());
        }
        dateRanges = RangeUtils.getDateRanges(fromDate, testDate, Period.SINGLE);
        assertEquals(1, dateRanges.size());
        PrintUtils.println(dateRanges);
    }

    @Test
    public void getDateRanges_week() {

        testDate = buildDate(simpleTestDate, simpleTestDateFormat);
        Date fromDate = buildDate("2012-12-01", simpleTestDateFormat);
        List<Range<Date>> dateRanges = RangeUtils.getDateRanges(fromDate, testDate, Period.WEEK);
        assertEquals(4, dateRanges.size());
        PrintUtils.println(dateRanges);
    }

    @Test
    public void getDateRanges_month() {

        testDate = buildDate(simpleTestDate, simpleTestDateFormat);
        Date fromDate = buildDate("2012-11-01", simpleTestDateFormat);
        List<Range<Date>> dateRanges = RangeUtils.getDateRanges(fromDate, testDate, Period.MONTH);
        assertEquals(2, dateRanges.size());
        PrintUtils.println(dateRanges);
    }

    @Test
    public void getDateRanges_year() {

        testDate = buildDate(simpleTestDate, simpleTestDateFormat);
        Date fromDate = buildDate("2010-12-01", simpleTestDateFormat);
        List<Range<Date>> dateRanges = RangeUtils.getDateRanges(fromDate, testDate, Period.YEAR);
        assertEquals(3, dateRanges.size());
        PrintUtils.println(dateRanges);
    }

    @Test
    public void getDatePeriod() {

        testDate = buildDate(simpleTestDate, simpleTestDateFormat);
        Date weekEnd = buildDate("2012-12-23", simpleTestDateFormat);
        Date weekStart = DateUtils.getDateOfDaysBack(6, weekEnd);
        Date monthEnd = buildDate("2012-12-31", simpleTestDateFormat);
        Date monthStart = buildDate("2012-12-01", simpleTestDateFormat);
        Date yearStart = buildDate("2012-01-01", simpleTestDateFormat);
        Range<Date> dayRange = RangeUtils.getDatePeriod(testDate, Period.DAY);
        assertEquals(testDate, dayRange.getLowerBound());
        assertEquals(testDate, dayRange.getUpperBound());
        Range<Date> weekRange = RangeUtils.getDatePeriod(testDate, Period.WEEK);
        assertEquals(weekStart, weekRange.getLowerBound());
        assertEquals(weekEnd, weekRange.getUpperBound());
        Range<Date> monthRange = RangeUtils.getDatePeriod(testDate, Period.MONTH);
        assertEquals(monthStart, monthRange.getLowerBound());
        assertEquals(monthEnd, monthRange.getUpperBound());
        Range<Date> yearRange = RangeUtils.getDatePeriod(testDate, Period.YEAR);
        assertEquals(yearStart, yearRange.getLowerBound());
        assertEquals(monthEnd, yearRange.getUpperBound());
    }

    @Test
    public void getLongRanges() {

        int expectedSize = 12;
        Long from = 0L;
        Long to = 11L;
        int step = 1;
        List<Range<Long>> longRanges = RangeUtils.getLongRanges(from, to, step);
        assertEquals(expectedSize, longRanges.size());
        for (int i = 0; i < expectedSize; i++) {
            Long expectedValue = (long) i;
            assertEquals(expectedValue, longRanges.get(i).getLowerBound());
            assertEquals(expectedValue, longRanges.get(i).getUpperBound());
        }

        try {
            RangeUtils.getLongRanges(to, from, step);
        } catch (Exception e) {
            assertEquals(InvalidRangeException.class, e.getClass());
        }
        longRanges = RangeUtils.getLongRanges(from, to, 9999);
        assertEquals(1, longRanges.size());
        PrintUtils.println(longRanges);

        step = 2;
        expectedSize = 6;
        List<Range<Long>> longRanges1 = RangeUtils.getLongRanges(from, to, step);
        assertEquals(expectedSize, longRanges1.size());
        for (int i = 0; i < expectedSize; i++) {
            Long expectedFrom = (long) i * step;
            Long expectedTo = expectedFrom + step - 1;
            assertEquals(expectedFrom, longRanges1.get(i).getLowerBound());
            assertEquals(expectedTo, longRanges1.get(i).getUpperBound());
        }

        step = 5;
        expectedSize = 3;
        List<Range<Long>> longRanges2 = RangeUtils.getLongRanges(from, to, step);
        assertEquals(expectedSize, longRanges2.size());
        for (int i = 0; i < expectedSize; i++) {
            Long expectedFrom = (long) i * step;
            Long tempExpectedTo = expectedFrom + step - 1;
            Long expectedTo = tempExpectedTo > to ? to : tempExpectedTo;
            assertEquals(expectedFrom, longRanges2.get(i).getLowerBound());
            assertEquals(expectedTo, longRanges2.get(i).getUpperBound());
        }

        step = -1;
        expectedSize = 12;
        List<Range<Long>> longRanges3 = RangeUtils.getLongRanges(from, to, step);
        assertEquals(expectedSize, longRanges3.size());
        for (int i = 0; i < expectedSize; i++) {
            Long expectedValue = (long) i;
            assertEquals(expectedValue, longRanges3.get(i).getLowerBound());
            assertEquals(expectedValue, longRanges3.get(i).getUpperBound());
        }
    }

    private Date buildDate(final String dateString, final String pattern) {

        return DateUtils.getDateFromString(dateString, pattern);
    }
}
