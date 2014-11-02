package org.codelogger.utils.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.codelogger.utils.DateUtils;

import org.junit.Test;

public class PeriodTest {

    @Test
    public void getInstance() {

        Period day = Period.DAY;
        Period week = Period.WEEK;
        Period month = Period.MONTH;
        Period year = Period.YEAR;
        Period single = Period.SINGLE;
        assertNotNull(day);
        assertNotNull(week);
        assertNotNull(month);
        assertNotNull(year);
        assertNotNull(single);
    }

    @Test
    public void values() {

        int expectedSize = 5;
        Period[] expectedResult = { Period.DAY, Period.WEEK, Period.MONTH, Period.YEAR,
                Period.SINGLE };
        Period[] values = Period.values();
        assertEquals(expectedSize, values.length);
        for (int i = 0; i < values.length; i++) {
            assertEquals(expectedResult[i], values[i]);
        }
    }

    @Test
    public void getPeriod() {

        Period day = Period.getPeriod(Calendar.DAY_OF_MONTH);
        assertEquals(Period.DAY, day);
        Period week = Period.getPeriod(Calendar.WEEK_OF_YEAR);
        assertEquals(Period.WEEK, week);
        Period month = Period.getPeriod(Calendar.MONTH);
        assertEquals(Period.MONTH, month);
        Period year = Period.getPeriod(Calendar.YEAR);
        assertEquals(Period.YEAR, year);
        Period single = Period.getPeriod(-1);
        assertEquals(Period.SINGLE, single);
        try {
            Period.getPeriod(Calendar.HOUR);
        } catch (Exception e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void getValue() {

        Date date = DateUtils.getDateFromString("2012-12-21", "yyyy-MM-dd");
        Period day = Period.DAY;
        int dayValue = day.getValue(date);
        assertEquals(21, dayValue);
        Period week = Period.WEEK;
        int weekValue = week.getValue(date);
        assertEquals(52, weekValue);
        Period month = Period.MONTH;
        int monthValue = month.getValue(date);
        assertEquals(12, monthValue);
        Period year = Period.YEAR;
        int yearValue = year.getValue(date);
        assertEquals(2012, yearValue);
    }

    @Test
    public void testToString() {

        System.out.println(Period.DAY);
    }
}
