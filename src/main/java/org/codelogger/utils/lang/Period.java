package org.codelogger.utils.lang;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 粒度周期
 * 
 * @author DengDefei
 * 
 */
public enum Period {

    DAY("DAY", Calendar.DAY_OF_MONTH),

    WEEK("WEEK", Calendar.WEEK_OF_YEAR),

    MONTH("MONTH", Calendar.MONTH),

    YEAR("YEAR", Calendar.YEAR),

    SINGLE("SINGLE", -1);

    public final int value;

    private final Calendar calendar;

    private Period(final String name, final int calendarField) {

        calendar = GregorianCalendar.getInstance(Locale.CHINA);
        value = calendarField;
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
    }

    public static Period getPeriod(final int periodValue) {

        switch (periodValue) {
        case Calendar.DAY_OF_MONTH:
            return DAY;
        case Calendar.WEEK_OF_YEAR:
            return WEEK;
        case Calendar.MONTH:
            return MONTH;
        case Calendar.YEAR:
            return YEAR;
        case -1:
            return SINGLE;
        }
        throw new IllegalArgumentException(String.format(
                "Unable to generate Period for integer: %s", periodValue));
    }

    public int getValue(final Date date) {

        calendar.setTime(date);
        int fieldValue = calendar.get(value);
        return value == Calendar.MONTH ? fieldValue + 1 : fieldValue;
    }
}