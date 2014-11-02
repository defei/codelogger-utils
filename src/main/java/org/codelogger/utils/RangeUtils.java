package org.codelogger.utils;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Calendar.DAY_OF_MONTH;
import static org.codelogger.utils.DateUtils.formatToStartOfDay;

import com.google.common.collect.Lists;
import org.codelogger.utils.beans.Range;
import org.codelogger.utils.exceptions.InvalidRangeException;
import org.codelogger.utils.lang.Period;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

public class RangeUtils {

  private static final Vector<Calendar> calendarCache = new Vector<Calendar>(2);

  private RangeUtils() {

  }

  /**
   * Returns Range&lt;Date&gt; list between given date from and date to by
   * period granulation.
   *
   * @param from the range from.
   * @param to the range to.
   * @param periodGranulation the range granulation.
   * @return Range&lt;Date&gt; list between given date from and date to by
   * period granulation.
   */
  public static List<Range<Date>> getDateRanges(Date from, Date to, final Period periodGranulation)
      throws InvalidRangeException {

    if (from.after(to)) {
      throw buildInvalidRangeException(from, to);
    }
    if (periodGranulation == Period.SINGLE) {
      @SuppressWarnings("unchecked") ArrayList<Range<Date>> list =
          newArrayList(Range.getInstance(from, to));
      return list;
    }
    to = formatToStartOfDay(to);
    from = formatToStartOfDay(from);
    List<Range<Date>> dateRanges = Lists.newArrayList();
    Calendar calendar = buildCalendar(from);
    Date currentProcessDate = calendar.getTime();
    for (Date processEndDate = getDatePeriod(to, periodGranulation).getUpperBound(); !processEndDate
        .before(currentProcessDate); currentProcessDate = calendar.getTime()) {
      Date dateInRange = calendar.getTime();
      Range<Date> dateRange = getDatePeriod(dateInRange, periodGranulation);
      dateRanges.add(dateRange);
      calendar.add(periodGranulation.value, 1);
    }
    calendarCache.add(calendar);
    Range<Date> firstRange = dateRanges.get(0);
    if (firstRange.getLowerBound().before(from)) {
      dateRanges.set(0, Range.getInstance(from, firstRange.getUpperBound()));
    }
    int indexOfLastDateRange = dateRanges.size() - 1;
    Range<Date> lastRange = dateRanges.get(indexOfLastDateRange);
    if (lastRange.getUpperBound().after(to)) {
      dateRanges.set(indexOfLastDateRange, Range.getInstance(lastRange.getLowerBound(), to));
    }
    return dateRanges;
  }

  private static InvalidRangeException buildInvalidRangeException(final Object from,
      final Object to) {

    return new InvalidRangeException(String.format("From span: %s is after to span: %s", from, to));
  }

  /**
   * Return Range&lt;Date&gt; by given date and period.
   *
   * @param date the date belong to the range.
   * @param period the range granulation.
   * @return a Range&lt;Date&gt; by given date and period.
   */
  public static Range<Date> getDatePeriod(final Date date, final Period period) {

    Calendar calendar = buildCalendar(date);
    Range<Date> dateRange = null;
    Date startDate = calendar.getTime();
    Date endDate = calendar.getTime();
    if (period != Period.DAY) {
      for (; period.getValue(date) == period.getValue(calendar.getTime()); calendar
          .add(DAY_OF_MONTH, 1)) {
        endDate = calendar.getTime();
      }

      calendar.setTime(date);
      for (; period.getValue(date) == period.getValue(calendar.getTime()); calendar
          .add(DAY_OF_MONTH, -1)) {
        startDate = calendar.getTime();
      }
    }
    calendarCache.add(calendar);
    dateRange = Range.getInstance(startDate, endDate);
    return dateRange;
  }

  /**
   * Returns Range&lt;Long&gt; list between given from and to by step.The
   * minimum step is 1.<br/>
   * e.g:<br/>
   * &nbsp;&nbsp;&nbsp;&nbsp;[from:0,to:3,step:1] => [[0-0],[1-1],[2-2],[3-3]]<br/>
   * &nbsp;&nbsp;&nbsp;&nbsp;[from:0,to:7,step:2] => [[0-1],[2-3],[4-5],[6-7]]<br/>
   * &nbsp;&nbsp;&nbsp;&nbsp;[from:0,to:7,step:3] => [[0-2],[3-5],[6-7]]<br/>
   *
   * @param from the range from.
   * @param to the range to.
   * @param step the range granulation.
   * @return a Range&lt;Long&gt; list between given from and to by step.
   */
  public static List<Range<Long>> getLongRanges(final Long from, final Long to, final int step)
      throws InvalidRangeException {

    if (from > to) {
      throw buildInvalidRangeException(from, to);
    }
    ArrayList<Range<Long>> list = newArrayList();
    if (step >= to - from) {
      list.add(Range.getInstance(from, to));
    } else {
      for (Long currentSpan = from; currentSpan <= to; ) {
        Long lowerBound = currentSpan;
        Long tempUpperBound = currentSpan + (step > 1 ? step - 1 : 0);
        Long upperBound = tempUpperBound > to ? to : tempUpperBound;
        list.add(Range.getInstance(lowerBound, upperBound));
        currentSpan += step > 0 ? step : 1;
      }
    }
    return list;
  }

  /**
   * Gets a calendar using the default time zone and locale. The Calendar
   * returned is based on the given time in the default time zone with the
   * default locale.
   *
   * @return an calendar instance.
   */
  private static Calendar buildCalendar(final Date date) {

    Calendar calendar = buildCalendar();
    calendar.setTime(date);
    return calendar;
  }

  /**
   * Gets a calendar using the default time zone and locale. The Calendar
   * returned is based on the current time in the default time zone with the
   * default locale.
   *
   * @return a calendar instance.
   */
  private static synchronized Calendar buildCalendar() {

    if (calendarCache.isEmpty()) {
      return GregorianCalendar.getInstance();
    } else {
      Calendar calendar = calendarCache.firstElement();
      calendarCache.remove(0);
      return calendar;
    }
  }

}
