package com.plg.shiro.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static DateFormat dateFullTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static DateFormat simpleDotFormatter = new SimpleDateFormat("yyyy.MM");

	public static final String PATTERN_DATE = "yyyy-MM-dd";

	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";


	public static Date parseHM(String dateString) {

		Date date = null;
		try {
			date = dateTimeFormat.parse(dateString);
		} catch (ParseException e) {
		}
		return date;
	}


	public static String date2String(Date date, String pattern) {

		if (date == null) {
			return null;
		}
		if ((pattern == null) || (pattern.equals(""))) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}


	public static Date string2Date(String strDate, String pattern) {

		if ((strDate == null) || (strDate.equals(""))) {
			return null;
		}
		if ((pattern == null) || (pattern.equals(""))) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
		return date;
	}


	public static int dateToDateYear(Date nowDate, Date date) {

		if (nowDate == null)
			return 0;
		if (date == null)
			return 0;
		long livetime = nowDate.getTime() - date.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(livetime);
		return calendar.get(1) - 1970;
	}


	public static Date addYear(int years) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	
	public static Date addYear(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	public static Date addDay(int num) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}
	
	public static Date addDay(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}

	public static Date addMonth(int num) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}
	
	public static Date addMonth(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}

	public static Date subDate(Date date, int type, int subNum) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, subNum);
		return calendar.getTime();
	}


	public static String format(Date date, String pattern) {

		if (date == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}


	public static String format(Object obj, String pattern) {

		if (obj == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(obj);
	}


	public static Date parse(String dateString, String pattern) {

		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (Throwable t) {
			date = null;
		}
		return date;
	}


	public static Date[] getDayPeriod(Date date) {

		if (date == null) {
			return null;
		}
		Date[] dtary = new Date[2];
		dtary[0] = getDayMinTime(date);
		dtary[1] = getDayMaxTime(date);
		return dtary;
	}


	public static Date getSpecifiedTime(Date date, int hours, int minutes, int seconds) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, hours);
		c.set(12, minutes);
		c.set(13, seconds);
		return c.getTime();
	}


	public static Date getDayMinTime(Date date) {

		return getSpecifiedTime(date, 0, 0, 0);
	}


	public static Date getDayMaxTime(Date date) {

		return getSpecifiedTime(date, 23, 59, 59);
	}


	public static Date[] getWeekPeriod(Date dt) {

		if (dt == null)
			return null;
		Date[] dtary = new Date[2];
		Calendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(2);
		calendar.setTime(dt);
		while (calendar.get(7) != 2) {
			calendar.add(5, -1);
		}
		dtary[0] = getDayMinTime(calendar.getTime());
		calendar.add(7, 6);
		dtary[1] = getDayMaxTime(calendar.getTime());
		return dtary;
	}


	public static Date parseHSDate(String dtStr) {

		if ((dtStr == null) || (dtStr.equals(""))) {
			return new Date();
		}
		int year = Integer.parseInt(dtStr.substring(0, 4)) - 1900;
		int month = Integer.parseInt(dtStr.substring(4, 6)) - 1;
		int date = Integer.parseInt(dtStr.substring(6));
		return new Date(year, month, date);
	}


	public static Date[] getMonthPeriod(Date dt) {

		int[] days = { 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (dt == null)
			return null;
		Date[] dtary = new Date[2];
		dtary[0] = new Date(dt.getYear(), dt.getMonth(), 1, 0, 0, 0);
		dtary[1] = new Date(dt.getYear(), dt.getMonth(), days[dt.getMonth()], 23, 59, 59);
		if ((dt.getMonth() == 1) && (dt.getYear() % 4 == 0)) {
			dtary[1].setDate(29);
		}
		return dtary;
	}


	public static boolean isSameDay(Date first, Date second) {

		Date[] range = getDayPeriod(first);
		return (second.after(range[0])) && (second.before(range[1]));
	}


	public static boolean isSameWeek(Date first, Date second) {

		Date[] range = getWeekPeriod(first);
		return (compare(second, range[0]) >= 0) && (compare(second, range[1]) <= 0);
	}


	public static boolean isSameMonth(Date first, Date second) {

		return (first.getYear() == second.getYear()) && (first.getMonth() == second.getMonth());
	}


	public static Date getDateAfterMonth(Date date, int amount) {

		if (date == null) {
			throw new RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, amount);
		return calendar.getTime();
	}


	public static Date getDateAfterDate(Date date, int amount) {

		if (date == null) {
			throw new RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, amount);
		return calendar.getTime();
	}


	public static boolean isSameQuarter(Date first, Date second) {

		if (first.getYear() != second.getYear()) {
			return false;
		}
		if ((first.getMonth() <= 2) && (second.getMonth() <= 2))
			return true;
		if ((first.getMonth() <= 5) && (second.getMonth() <= 5) && (first.getMonth() > 2) && (second.getMonth() > 2))
			return true;
		if ((first.getMonth() <= 8) && (second.getMonth() <= 8) && (first.getMonth() > 5) && (second.getMonth() > 5))
			return true;
		if ((first.getMonth() <= 11) && (second.getMonth() <= 11) && (first.getMonth() > 8) && (second.getMonth() > 8)) {
			return true;
		}
		return false;
	}


	public static Date getLastDayOfWeek(Date date) {

		return new Date(date.getYear(), date.getMonth(), date.getDate() + (5 - date.getDay()));
	}


	public static Date getLastDayOfMonth(Date date) {

		return new Date(date.getYear(), date.getMonth() + 1, 0);
	}


	public static Date getFirstDayOfQuarter(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(2, c.get(2) / 3 * 3);
		c.set(5, 1);
		return c.getTime();
	}


	public static Date getLastDayOfQuarter(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(2, (c.get(2) / 3 + 1) * 3);
		c.set(5, 0);
		return c.getTime();
	}


	public static Date getFirstDayOfYear(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(2, 0);
		c.set(5, 1);
		return c.getTime();
	}


	public static Date getLastDayOfYear(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(2, 11);
		c.set(5, 31);
		return c.getTime();
	}


	public static int compare(Date first, Date second) {

		if (first.getYear() > second.getYear())
			return 1;
		if (first.getYear() < second.getYear()) {
			return -1;
		}
		if (first.getMonth() > second.getMonth())
			return 1;
		if (first.getMonth() < second.getMonth()) {
			return -1;
		}
		if (first.getDate() > second.getDate())
			return 1;
		if (first.getDate() < second.getDate()) {
			return -1;
		}
		return 0;
	}


	public static Date getAbsoluteDate(Date now, int field, int amount) {

		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(field, amount);
		return c.getTime();
	}


	public static Date setAbsoluteDate(Date now, int field, int amount) {

		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(field, amount);
		return c.getTime();
	}


	public static Date getDateTimeByType(String type, int value) {

		Calendar c = Calendar.getInstance();
		if ("hour".equals(type)) {
			c.add(11, value);
		} else {
			c.add(5, value);
		}
		return c.getTime();
	}


	public static boolean validate(String dateString) {

		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		Matcher m = p.matcher(dateString);
		if (!m.matches()) {
			return false;
		}
		String[] array = dateString.split("-");
		int year = Integer.valueOf(array[0]).intValue();
		int month = Integer.valueOf(array[1]).intValue();
		int day = Integer.valueOf(array[2]).intValue();
		if ((month < 1) || (month > 12)) {
			return false;
		}
		int[] monthLengths = { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (isLeapYear(year)) {
			monthLengths[2] = 29;
		} else {
			monthLengths[2] = 28;
		}
		int monthLength = monthLengths[month];
		if ((day < 1) || (day > monthLength)) {
			return false;
		}
		return true;
	}


	public static boolean isLeapYear(int year) {

		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}


	public static String getYesterday() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date temp = new Date();
		try {
			temp = new Date(sdf.parse(sdf.format(new Date())).getTime() - 86400000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(temp);
	}


	public static String getPreWeek() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date temp = new Date();
		try {
			temp = new Date(sdf.parse(sdf.format(new Date())).getTime() - 604800000L);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(temp);
	}


	//根据年龄返算出生日期
	public static Date getBirthDateByAge(int age) {
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		yearNow = yearNow - age;
		cal.set(Calendar.YEAR, yearNow);
		return cal.getTime();
	}

	public static int getMonthSpace(String start, String end, String format) throws ParseException {
        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(start));
        c2.setTime(sdf.parse(end));
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        
        int y = year2 - year1;
        if(y==0) {
        	return month2 - month1;
        }
        if(month1>month2) {
        	y--;
        	result = y*12 + (month2+12) - month1;
        } else {
        	result = y*12 + month2 - month1;
        }

        return result;

    }
	
	public static int getYearSpace(String start, String end, String format) throws ParseException {
        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(start));
        c2.setTime(sdf.parse(end));

        result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        return result;

    }
	
	public static Date dateParse(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if ((pattern == null) || (pattern.equals(""))) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return string2Date(date2String(date,pattern),pattern);
	}

	public static void main(String args[]) throws Exception {
		System.out.println(date2String(getBirthDateByAge(4), "yyyy-MM-dd") );
		
		System.out.println(getMonthSpace("201701", "201812", "yyyyMM"));
		System.out.println(getYearSpace("2012", "2019", "yyyy"));
	}
}
