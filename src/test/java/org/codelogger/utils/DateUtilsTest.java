package org.codelogger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateUtilsTest implements Runnable {

    private String name;

    private final String simpleTestDate = "2012-12-21";

    private final String simpleTestDateFormat = "yyyy-MM-dd";

    private final String fullTestDate = "2012-12-21 00:00:00";

    private final String fullTestDateFormat = "yyyy-MM-dd HH:mm:ss";

    private Date testDate;

    public static void main(final String[] args) {

        DateUtilsTest concurrencyRunner1 = new DateUtilsTest();
        concurrencyRunner1.setName("Thread1");
        DateUtilsTest concurrencyRunner2 = new DateUtilsTest();
        concurrencyRunner2.setName("Thread2");
        DateUtilsTest concurrencyRunner3 = new DateUtilsTest();
        concurrencyRunner3.setName("Thread3");
        DateUtilsTest concurrencyRunner4 = new DateUtilsTest();
        concurrencyRunner4.setName("Thread4");
        Thread thread1 = new Thread(concurrencyRunner1);
        Thread thread2 = new Thread(concurrencyRunner2);
        Thread thread3 = new Thread(concurrencyRunner3);
        Thread thread4 = new Thread(concurrencyRunner4);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    @Override
    public void run() {

        long times = 1000000;
        Monitor monitor = MonitorUtils.getMonitor(DateUtils.class.getSimpleName(), "subMinutes");
        monitor.start();
        for (long i = 0; i < times; i++) {
            testDate = buildDate("2012-01-01 23:59:59", fullTestDateFormat);
            Date testDate1 = buildDate("2012-01-01 23:00:00", fullTestDateFormat);
            long subMinutes = DateUtils.subMinutes(testDate, testDate1);
            assertEquals(59, subMinutes);
        }
        monitor.stopAndprintResult("%s run %s times", name, times);
    }

    @Before
    public void setup() throws ParseException {

        testDate = buildDate(simpleTestDate, simpleTestDateFormat);
    }

    @Test
    public void getDateFromString() throws ParseException {

        Date dateFromString = DateUtils.getDateFromString(fullTestDate);
        assertEquals(testDate, dateFromString);
    }

    @Test
    public void getDateFromString_givenDataFormat_returnData() {

        Date dateFromString = DateUtils.getDateFromString(simpleTestDate, simpleTestDateFormat);
        assertEquals(testDate, dateFromString);
    }

    @Test
    public void getDateFormat() throws ParseException {

        String dateFormat = DateUtils.getDateFormat(testDate, fullTestDateFormat);
        assertEquals(fullTestDate, dateFormat);
    }

    @Test
    public void getDateFormat_byStringDate() throws ParseException {

        String expectedDateFormat = "Dec 21 23:59:59";
        String dateFormat = DateUtils.getDateFormat(fullTestDate, fullTestDateFormat,
                "MMM dd 23:59:59");
        assertEquals(expectedDateFormat, dateFormat);
    }

    @Test
    public void getDateOfSecondsBack() throws ParseException {

        Date expectDate = buildDate("2012-12-20 23:59:35", fullTestDateFormat);
        Date dateOfSecondsBack = DateUtils.getDateOfSecondsBack(25, testDate);
        assertEquals(expectDate, dateOfSecondsBack);
    }

    @Test
    public void getDateOfSecondsBack1() throws ParseException {

        Date expectDate = buildDate("2012-12-21 00:00:25", fullTestDateFormat);
        Date dateOfSecondsBack = DateUtils.getDateOfSecondsBack(-25, testDate);
        assertEquals(expectDate, dateOfSecondsBack);
    }

    @Test
    public void getDateOfMinutesBack() throws ParseException {

        Date expectDate = buildDate("2012-12-20 23:55:00", fullTestDateFormat);
        Date dateOfMinutesBack = DateUtils.getDateOfMinutesBack(5, testDate);
        assertEquals(expectDate, dateOfMinutesBack);
    }

    @Test
    public void getDateOfMinutesBack1() throws ParseException {

        Date expectDate = buildDate("2012-12-21 00:05:00", fullTestDateFormat);
        Date dateOfMinutesBack = DateUtils.getDateOfMinutesBack(-5, testDate);
        assertEquals(expectDate, dateOfMinutesBack);
    }

    @Test
    public void getDateOfHoursBack() throws ParseException {

        Date expectDate = buildDate("2012-12-20 19:00:00", fullTestDateFormat);
        Date dateOfHoursBack = DateUtils.getDateOfHoursBack(5, testDate);
        assertEquals(expectDate, dateOfHoursBack);
    }

    @Test
    public void getDateOfHoursBack1() throws ParseException {

        Date expectDate = buildDate("2012-12-21 5:00:00", fullTestDateFormat);
        Date dateOfHoursBack = DateUtils.getDateOfHoursBack(-5, testDate);
        assertEquals(expectDate, dateOfHoursBack);
    }

    @Test
    public void getDateOfDaysBack() throws ParseException {

        Date expectDate = buildDate("2012-12-16", simpleTestDateFormat);
        Date dateOfDaysBack = DateUtils.getDateOfDaysBack(5, testDate);
        assertEquals(expectDate, dateOfDaysBack);
    }

    @Test
    public void getDateOfDaysBack1() throws ParseException {

        Date expectDate = buildDate("2012-12-26", simpleTestDateFormat);
        Date dateOfDaysBack = DateUtils.getDateOfDaysBack(-5, testDate);
        assertEquals(expectDate, dateOfDaysBack);
    }

    @Test
    public void getDateOfWeeksBack() throws ParseException {

        Date expectDate = buildDate("2012-12-14", simpleTestDateFormat);
        Date dateOfWeeksBack = DateUtils.getDateOfWeeksBack(1, testDate);
        assertEquals(expectDate, dateOfWeeksBack);
    }

    @Test
    public void getDateOfWeeksBack1() throws ParseException {

        Date expectDate = buildDate("2012-12-28", simpleTestDateFormat);
        Date dateOfWeeksBack = DateUtils.getDateOfWeeksBack(-1, testDate);
        assertEquals(expectDate, dateOfWeeksBack);
    }

    @Test
    public void getDateOfMonthsBack() throws ParseException {

        Date expectDate = buildDate("2012-11-21", simpleTestDateFormat);
        Date dateOfWeeksBack = DateUtils.getDateOfMonthsBack(1, testDate);
        assertEquals(expectDate, dateOfWeeksBack);
    }

    @Test
    public void getDateOfMonthsBack1() throws ParseException {

        Date expectDate = buildDate("2013-01-21", simpleTestDateFormat);
        Date dateOfMonthsBack = DateUtils.getDateOfMonthsBack(-1, testDate);
        assertEquals(expectDate, dateOfMonthsBack);
    }

    @Test
    public void getDateOfMonthsBack2() throws ParseException {

        testDate = buildDate("2012-03-31", simpleTestDateFormat);
        Date expectDate = buildDate("2012-02-29", simpleTestDateFormat);
        Date dateOfMonthsBack = DateUtils.getDateOfMonthsBack(1, testDate);
        assertEquals(expectDate, dateOfMonthsBack);
    }

    @Test
    public void getDateOfMonthsBack3() throws ParseException {

        testDate = buildDate("2011-03-31", simpleTestDateFormat);
        Date expectDate = buildDate("2011-02-28", simpleTestDateFormat);
        Date dateOfMonthsBack = DateUtils.getDateOfMonthsBack(1, testDate);
        assertEquals(expectDate, dateOfMonthsBack);
    }

    @Test
    public void getDateOfYearsBack() throws ParseException {

        Date expectDate = buildDate("2011-12-21", simpleTestDateFormat);
        Date dateOfYearsBack = DateUtils.getDateOfYearsBack(1, testDate);
        assertEquals(expectDate, dateOfYearsBack);
    }

    @Test
    public void getDateOfYearsBack1() throws ParseException {

        Date expectDate = buildDate("2013-12-21", simpleTestDateFormat);
        Date dateOfYearsBack = DateUtils.getDateOfYearsBack(-1, testDate);
        assertEquals(expectDate, dateOfYearsBack);
    }

    @Test
    public void getDateOfYearsBack2() throws ParseException {

        testDate = buildDate("2012-02-29", simpleTestDateFormat);
        Date expectDate = buildDate("2011-02-28", simpleTestDateFormat);
        Date dateOfYearsBack = DateUtils.getDateOfYearsBack(1, testDate);
        assertEquals(expectDate, dateOfYearsBack);
    }

    @Test
    public void getDateOfYearsBack3() throws ParseException {

        testDate = buildDate("2012-02-29", simpleTestDateFormat);
        Date expectDate = buildDate("2013-02-28", simpleTestDateFormat);
        Date dateOfYearsBack = DateUtils.getDateOfYearsBack(-1, testDate);
        assertEquals(expectDate, dateOfYearsBack);
    }

    @Test
    public void getDateOfYearsBack4() throws ParseException {

        testDate = buildDate("2011-02-28", simpleTestDateFormat);
        Date expectDate = buildDate("2012-02-28", simpleTestDateFormat);
        Date dateOfYearsBack = DateUtils.getDateOfYearsBack(-1, testDate);
        assertEquals(expectDate, dateOfYearsBack);
    }

    @Test
    public void getSecondOfMinute() {

        int dayOfMonth = DateUtils.getSecondOfMinute(testDate);
        assertEquals(0, dayOfMonth);
    }

    @Test
    public void getSecondOfMinute1() throws ParseException {

        testDate = buildDate("2012-12-21 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getSecondOfMinute(testDate);
        assertEquals(45, dayOfMonth);
    }

    @Test
    public void getMinuteOfHour() {

        int dayOfMonth = DateUtils.getMinuteOfHour(testDate);
        assertEquals(0, dayOfMonth);
    }

    @Test
    public void getMinuteOfHour1() throws ParseException {

        testDate = buildDate("2012-12-21 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getMinuteOfHour(testDate);
        assertEquals(21, dayOfMonth);
    }

    @Test
    public void getHourOfDay() {

        int dayOfMonth = DateUtils.getHourOfDay(testDate);
        assertEquals(0, dayOfMonth);
    }

    @Test
    public void getHourOfDay1() throws ParseException {

        testDate = buildDate("2012-12-21 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getHourOfDay(testDate);
        assertEquals(13, dayOfMonth);
    }

    @Test
    public void getDayOfWeek() {

        int dayOfMonth = DateUtils.getDayOfWeek(testDate);
        assertEquals(DateUtils.FRIDAY, dayOfMonth);
    }

    @Test
    public void getDayOfWeek1() throws ParseException {

        testDate = buildDate("2012-11-11 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getDayOfWeek(testDate);
        assertEquals(DateUtils.SUNDAY, dayOfMonth);
    }

    @Test
    public void getDayOfMonth() {

        int dayOfMonth = DateUtils.getDayOfMonth(testDate);
        assertEquals(21, dayOfMonth);
    }

    @Test
    public void getDayOfMonth1() throws ParseException {

        testDate = buildDate("2012-12-21 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getDayOfMonth(testDate);
        assertEquals(21, dayOfMonth);
    }

    @Test
    public void getDayOfYear() {

        int dayOfMonth = DateUtils.getDayOfYear(testDate);
        assertEquals(356, dayOfMonth);
    }

    @Test
    public void getDayOfYear1() throws ParseException {

        testDate = buildDate("2012-02-01 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getDayOfYear(testDate);
        assertEquals(32, dayOfMonth);
    }

    @Test
    public void getWeekOfMonth() {

        int dayOfMonth = DateUtils.getWeekOfMonth(testDate);
        assertEquals(4, dayOfMonth);
    }

    @Test
    public void getWeekOfMonth1() throws ParseException {

        testDate = buildDate("2012-11-01 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getWeekOfMonth(testDate);
        assertEquals(1, dayOfMonth);
    }

    @Test
    public void getWeekOfYear() throws ParseException {

        testDate = buildDate("2012-02-09 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getWeekOfYear(testDate);
        assertEquals(6, dayOfMonth);
    }

    @Test
    public void getMonthOfYear() throws ParseException {

        int dayOfMonth = DateUtils.getMonthOfYear(testDate);
        assertEquals(DateUtils.DECEMBER, dayOfMonth);
    }

    @Test
    public void getMonthOfYear1() throws ParseException {

        testDate = buildDate("2012-02-09 13:21:45", fullTestDateFormat);
        int dayOfMonth = DateUtils.getMonthOfYear(testDate);
        assertEquals(DateUtils.FEBRUARY, dayOfMonth);
    }

    @Test
    public void getYear() {

        int dayOfMonth = DateUtils.getYear(testDate);
        assertEquals(2012, dayOfMonth);
    }

    @Test
    public void isFirstDayOfTheMonth() {

        boolean firstDayOfTheMonth = DateUtils.isFirstDayOfTheMonth(testDate);
        assertFalse(firstDayOfTheMonth);
    }

    @Test
    public void isFirstDayOfTheMonth1() throws ParseException {

        testDate = buildDate("2011-02-01", simpleTestDateFormat);
        boolean firstDayOfTheMonth = DateUtils.isFirstDayOfTheMonth(testDate);
        assertTrue(firstDayOfTheMonth);
    }

    @Test
    public void isLastDayOfTheMonth() {

        boolean lastDayOfTheMonth = DateUtils.isLastDayOfTheMonth(testDate);
        assertFalse(lastDayOfTheMonth);
    }

    @Test
    public void isLastDayOfTheMonth1() throws ParseException {

        testDate = buildDate("2012-01-31 13:21:45", fullTestDateFormat);
        boolean lastDayOfTheMonth = DateUtils.isLastDayOfTheMonth(testDate);
        assertTrue(lastDayOfTheMonth);
    }

    @Test
    public void subSeconds() throws ParseException {

        testDate = buildDate("2012-01-01 23:59:59", fullTestDateFormat);
        Date testDate1 = buildDate("2012-01-01 23:59:00", fullTestDateFormat);
        long subDays = DateUtils.subSeconds(testDate, testDate1);
        assertEquals(59, subDays);
    }

    @Test
    public void subMinutes() throws ParseException {

        testDate = buildDate("2012-01-01 23:59:59", fullTestDateFormat);
        Date testDate1 = buildDate("2012-01-01 23:00:00", fullTestDateFormat);
        long subMinutes = DateUtils.subMinutes(testDate, testDate1);
        assertEquals(59, subMinutes);
    }

    @Test
    public void subHours() throws ParseException {

        testDate = buildDate("2012-01-01 23:59:59", fullTestDateFormat);
        Date testDate1 = buildDate("2012-01-01 00:00:00", fullTestDateFormat);
        long subHours = DateUtils.subHours(testDate, testDate1);
        assertEquals(23, subHours);
    }

    @Test
    public void subDays_byString() throws ParseException {

        long subDays = DateUtils.subDays("2012-01-31", "2012-01-01");
        assertEquals(30, subDays);
    }

    @Test
    public void subDays_byString1() throws ParseException {

        long subDays = DateUtils.subDays("2012-03-01", "2012-01-01");
        assertEquals(60, subDays);
    }

    @Test
    public void subDays_byString2() throws ParseException {

        long subDays = DateUtils.subDays("2012-01-01", "2012-03-01");
        assertEquals(60, subDays);
    }

    @Test
    public void subDays_byString3() throws ParseException {

        long subDays = DateUtils.subDays("2012-01-01 23:59:59", "2012-03-01 00:00:00");
        assertEquals(60, subDays);
    }

    @Test
    public void subDays_byString4() throws ParseException {

        long subDays = DateUtils.subDays("2012-01-01", "2012-01-01");
        assertEquals(0, subDays);
    }

    @Test
    public void subDays_byDate() throws ParseException {

        testDate = buildDate("2012-01-01", simpleTestDateFormat);
        Date testDate1 = buildDate("2012-01-31 13:21:45", fullTestDateFormat);
        long subDays = DateUtils.subDays(testDate, testDate1);
        assertEquals(30, subDays);
    }

    @Test
    public void subDays_byDate1() throws ParseException {

        testDate = buildDate("2012-03-01 23:59:59", fullTestDateFormat);
        Date testDate1 = buildDate("2012-01-01 00:00:00", fullTestDateFormat);
        long subDays = DateUtils.subDays(testDate, testDate1);
        assertEquals(60, subDays);
    }

    @Test
    public void subMonths() throws ParseException {

        testDate = buildDate("2012-03-01", simpleTestDateFormat);
        Date testDate1 = buildDate("2012-01-31 13:21:45", fullTestDateFormat);
        long subDays = DateUtils.subMonths(testDate, testDate1);
        assertEquals(2, subDays);
        subDays = DateUtils.subMonths("2012-12-21", "2012-01-31");
        assertEquals(11, subDays);
    }

    @Test
    public void subYears() throws ParseException {

        testDate = buildDate("2012-03-01", simpleTestDateFormat);
        Date testDate1 = buildDate("1000-01-31 13:21:45", fullTestDateFormat);
        long subDays = DateUtils.subYears(testDate, testDate1);
        assertEquals(1012, subDays);
        subDays = DateUtils.subYears("2012-12-21", "2011-11-11");
        assertEquals(13, subDays);
    }

    @Test
    public void formatToStartOfDay() {

        Date expectedDate = buildDate("1000-01-31 00:00:00", fullTestDateFormat);
        Date date = buildDate("1000-01-31 13:21:45", fullTestDateFormat);
        Date startOfDay = DateUtils.formatToStartOfDay(date);
        assertEquals(expectedDate, startOfDay);
    }

    @Test
    public void getCurrentDate() {

        Date currentDate = DateUtils.getCurrentDate();
        assertNotNull(currentDate);
    }

    private Date buildDate(final String dateString, final String pattern) {

        return DateUtils.getDateFromString(dateString, pattern);
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }
}
