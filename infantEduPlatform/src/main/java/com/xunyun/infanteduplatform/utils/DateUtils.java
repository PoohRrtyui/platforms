package com.xunyun.infanteduplatform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Assert;

/**
 * Date To String/String to Date
 * 
 * 
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static SimpleDateFormat FORMATTIME_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static SimpleDateFormat FORMATTIME_DATE_CN = new SimpleDateFormat(
			"yyyy年MM月dd日");
	private static SimpleDateFormat FORMATTIME_DATETIME = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat FORMARTTIME_TIMESTAMP = new SimpleDateFormat(
			"yyyyMMdd");

	private static SimpleDateFormat FORMATTIME_MS = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	private static Calendar c = Calendar.getInstance();

	/***
	 * 获取格式:yyyy-MM-dd 格式的时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		if (date == null) {
			return "";
		}
		return FORMATTIME_DATE.format(date);
	}


	public static String getDateCn(String date) throws ParseException {
		Assert.hasText(date, "日期不能为空");
		return FORMATTIME_DATE_CN.format(FORMARTTIME_TIMESTAMP.parse(date));
	}

	/***
	 * 获取格式:yyyy-MM-dd HH:mm:ss 格式的时间字符串(24小时制)
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return FORMATTIME_DATETIME.format(date);
	}

	/**
	 * 获取Timestamp 当前时间
	 * 
	 * @return
	 */
	public static String getTimestampString() {
		return FORMARTTIME_TIMESTAMP.format(new Date());
	}

	/**
	 * 获取任意时间Timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimestampString(Date date) {
		if (date == null) {
			return "";
		}
		return FORMARTTIME_TIMESTAMP.format(date);
	}

	/**
	 * 字符串转时间:yyyy-MM-dd 的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date) {
		if (isEmpty(date)) {
			return new Date();
		}
		try {
			return FORMATTIME_DATE.parse(date);
		} catch (ParseException e) {
		}

		return null;
	}

	/**
	 * 字符串转时间:yyyyMMddHHmmssSSS
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDateMs(String date) throws ParseException {
		if (isEmpty(date)) {
			return new Date();
		}
		return FORMATTIME_MS.parse(date);
	}

	/**
	 * 字符串转时间:yyyy-MM-dd HH:mm:ss 的时间
	 * 
	 * @param date
	 * @param time
	 *            是否需要时分秒
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String date, Boolean time)
			throws ParseException {
		if (isEmpty(date)) {
			return new Date();
		}
		if (time) {
			return FORMATTIME_DATETIME.parse(date);
		} else {
			return stringToDate(date);
		}
	}

	/**
	 * 接口数据更新时间解析
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		if (isEmpty(date)) {
			return null;
		}
		try {
			return stringToDate(date, true);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		if (month.length() == 1) {
			month = "0" + month;
		}
		return month;
	}

	/**
	 * 获取月份 注意这里不能使用成员变量c，要重新创建Calendar，否则出现线程安全问题
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		String month = String.valueOf(cl.get(Calendar.MONTH) + 1);
		if (month.length() == 1) {
			month = "0" + month;
		}
		return month;
	}

	/**
	 * 获取年份
	 * 
	 * @return
	 */
	public static String getYear() {
		c.setTimeInMillis(System.currentTimeMillis());
		return c.get(Calendar.YEAR) + "";
	}

	/**
	 * 获取年份 注意这里不能使用成员变量c，要重新创建Calendar，否则出现线程安全问题
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.YEAR) + "";
	}

	/**
	 * 获取第几周
	 * 
	 * @return
	 */
	public static String getWeekOfYear() {
		c.setTimeInMillis(System.currentTimeMillis());
		return c.get(Calendar.WEEK_OF_YEAR) + "";
	}

	/**
	 * 获取第几周
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public static String getWeekOfYear(int year) {
		c.set(Calendar.YEAR, year);
		return c.get(Calendar.WEEK_OF_YEAR) + "";
	}

	/**
	 * 获取儒略历时间
	 * 
	 * @param date
	 * @return
	 */
	public static Integer toJulianDate(Date date) {
		Assert.notNull(date);
		c.setTime(date);
		Integer y = c.get(Calendar.YEAR) - 1900;
		Integer jy = y * 1000;
		return jy + c.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 检查是否date2在date1日期以前
	 * 
	 * @param d1
	 * @param d2
	 * @return true date2在date1前
	 */
	public static boolean isBefore(Date d1, Date d2) {
		boolean ret = false;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if (c2.before(c1)) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 判断是否是月初
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFirstOfMonth(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		if (cl.get(Calendar.DATE) == cl.getMinimum(Calendar.DAY_OF_MONTH)) {
			return true;
		} else {
			return false;
		}
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, 1);
		cl.set(Calendar.DAY_OF_MONTH, 0);
		return cl.getTime();
	}

	/**
	 * 非空判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() <= 0) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(getWeekOfYear());
		// System.out.println(stringToDateMs("201501191641080000"));
		// System.out.println(toJulianDate(new Date()));
		//
		// new Thread(){public void run() {
		// String y = DateUtils.getMonth(DateUtils.stringToDate("2014-09-17"));
		// System.out.println(y+"------T1");
		// };}.start();
		// new Thread(){public void run() {
		// String y = DateUtils.getMonth(DateUtils.stringToDate("2015-10-17"));
		// System.out.println(y+"------T2");
		// };}.start();
		// new Thread(){public void run() {
		// String y = DateUtils.getMonth(DateUtils.stringToDate("2013-11-17"));
		// System.out.println(y+"------T3");
		// };}.start();

		System.out.println(DateUtils.getDate(DateUtils
				.getLastDayOfMonth(DateUtils.stringToDate("2015-08-23"))));
	}
}
