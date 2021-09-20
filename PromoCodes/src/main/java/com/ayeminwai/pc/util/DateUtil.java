package com.ayeminwai.pc.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("deprecation")
public class DateUtil extends Object {

	private static SimpleDateFormat standardDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat apiFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

	private static SimpleDateFormat dateValiFormat = null;

	public static Date convertDateStringToDate(String dateStr) {
		try {
			return standardDateFormat.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Date dateFormatValidation(String dateStr, String format) {
		try {
			dateValiFormat = new SimpleDateFormat(format);
			dateValiFormat.setLenient(false);
			return dateValiFormat.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Date convertDateStringToDate(String date, String month, String year) {
		try {
			String dateString = date + "/" + month + "/" + year;
			Date objDate = standardDateFormat.parse(dateString);
			return objDate;
		} catch (ParseException pe) {
			return null;
		}
	}

	public static String convertDateToDateString(Date date) {
		return standardDateFormat.format(date);
	}
	
	public static String convertDateStringddMMyyyyHHmmss() {
		return apiFormat.format(new Date());
	}

	public static int[] parseDate(Date date) {
		int dateArr[] = new int[3];
		dateArr[0] = date.getDate();
		dateArr[1] = date.getMonth();
		dateArr[2] = 1900 + date.getYear();

		return dateArr;
	}

	public static void main(String s[]) {

		Date now = new Date();
		System.out.println(now);
		Date addedValue = addDateTime(now, 43200);
		System.out.println(addedValue);

	}

	public static Date addMonths(final Date date, final int months) {
		Date calculatedDate = null;

		if (date != null) {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, months);
			calculatedDate = new Date(calendar.getTime().getTime());
		}

		return calculatedDate;
	}

	public static Date convertAPIDateStringToDate(String dateStr) {
		try {
			return apiFormat.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static String getStrDate(String format, Date date) {
		return (new SimpleDateFormat(format).format(date)).toUpperCase();
	}

	public static long calDifferenceBetweenTwoDates(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	public static long calDiffInMinBetweenTwoDates(Date startDate, Date endDate) {

		long diff = endDate.getTime() - startDate.getTime();
		long diffMin = diff / (60 * 60 * 1000);

		return diffMin;
	}

	public static long calDifferenceInMin(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		long diffMin = diff / (60 * 1000);
		return diffMin;
	}

	public static String getCurrentTimeStamp() {
		return (new Timestamp(new Date().getTime()).toString());
	}

	public static Date getGracePeriodEndDate(Date billingDate, int gracePeriod) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(billingDate);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		cal.add(Calendar.DATE, gracePeriod);

		return cal.getTime();

	}

	public static Date getCurrentDateNoTime() {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}

	public static Date addDateTime(Date datetime, int value) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);

		cal.add(Calendar.MINUTE, value);

		return cal.getTime();

	}

	static final long ONE_HOUR = 60 * 60 * 1000L;

	// usage ::
	// System.out.println(daysBetween("12/01/2004","16/01/2004","dd/MM/yyyy"));
	public static long daysBetween(String startDate, String endDate, String format) {
		long diffDays = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d1 = sdf.parse(startDate);
			Date d2 = sdf.parse(endDate);
			diffDays = ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return diffDays;
	}

}