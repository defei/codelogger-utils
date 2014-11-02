package org.codelogger.utils.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DatePeriodTest {

    @Test
    public void validateValue() {

        long milliseconds_in_one_seconds = 1000;
        assertEquals(DatePeriod.SECOND, milliseconds_in_one_seconds);
        long milliseconds_in_one_minute = milliseconds_in_one_seconds * 60;
        assertEquals(DatePeriod.MINUTE, milliseconds_in_one_minute);
        long milliseconds_in_one_hour = milliseconds_in_one_minute * 60;
        assertEquals(DatePeriod.HOUR, milliseconds_in_one_hour);
        long milliseconds_in_one_day = milliseconds_in_one_hour * 24;
        assertEquals(DatePeriod.DAY, milliseconds_in_one_day);
        assertEquals(DatePeriod.WEEK, milliseconds_in_one_day * 7);
    }
}
