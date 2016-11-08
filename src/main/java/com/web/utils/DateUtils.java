package com.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.web.core.config.BizExpDictionary;
import com.web.core.exception.BizException;

public class DateUtils {
	private static final String YMD = "yyyyMMdd";
	private static final String YYMMDD = "yyyy-MM-dd";
	private static final String YMDHMS = "yyyyMMddHHmmss";
	private static final String YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static String getDateStrByPattern(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date getDateByPattern(Date date, String pattern)
			throws ParseException {
		return new SimpleDateFormat(pattern)
				.parse(new SimpleDateFormat(pattern).format(date));
	}

	public static Date getDateByPattern(String date, String pattern)
			throws ParseException {
		return new SimpleDateFormat(pattern).parse(date);
	}

	public static Date addDate(Date date, int type, int value) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		switch (type) {
		case 0:
			// 增加年
			c.add(Calendar.YEAR, value);
			break;
		case 1:
			// 增加月
			c.add(Calendar.MONTH, value);
			break;
		case 2:
			// 日
			c.add(Calendar.DATE, value); // 日期加1天
			break;
		default:
			c.add(Calendar.YEAR, 3);
			break;
		}

		date = c.getTime();
		return date;
	}

	public static Date getLast(Date date, int type) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String edate = "";
		if (null == date) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		calendar.setTime(new Date());

		// 设置日期为本月最大日期
		switch (type) {
		case 0:
			// 增加年
			int year = calendar.get(Calendar.YEAR);
			edate = year + "-12-31";

			break;
		case 1:
			// 增加月
			calendar.set(Calendar.DATE,
					calendar.getActualMaximum(Calendar.DATE));
			edate = fmt.format(calendar.getTime());
			break;

		default:
			edate = fmt.format(date);
			break;
		}
		try {
			date = fmt.parse(edate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date addYearDate(Date date, int value) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int year = c.get(Calendar.YEAR);
		String edate = year + value - 1 + "-12-31";
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = fmt.parse(edate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long dateBetween(String startDate, String endDate)
			throws ParseException {
		long date = 0;
		if (startDate != null && endDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			long to = df.parse(endDate).getTime();
			long from = df.parse(startDate).getTime();
			date = (to - from) / (1000 * 60 * 60 * 24);
		}
		return date;
	}

	public static long monthBetween(String startDate, String endDate)
			throws ParseException {
		long month = 0;
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar bef = Calendar.getInstance();
			Calendar aft = Calendar.getInstance();
			bef.setTime(sdf.parse(startDate));
			aft.setTime(sdf.parse(endDate));
			int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
			int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);

			month = (year * 12) + result;
			if (month == 0) {
				month = 1;
			}
		}
		return month;

	}

	public static boolean between(Date startDate, Date endDate, Date beteenDate) {
		boolean flag = false;

		try {
			Calendar cstart = Calendar.getInstance();
			cstart.setTime(startDate);
			cstart.set(Calendar.DATE, cstart.get(Calendar.DATE) - 1);
			Calendar cend = Calendar.getInstance();
			cend.setTime(endDate);
			cend.add(Calendar.DATE, 1);
			startDate = cstart.getTime();
			endDate = cend.getTime();
			if (startDate.before(beteenDate) && endDate.after(beteenDate)) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:11:04
	 */
	public static Date getCurrYearFirstDay() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirstDay(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:11:04
	 */
	public static Date getCurrYearLastDay() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLastDay(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:11:04
	 */
	public static Date getYearFirstDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:11:04
	 */
	public static Date getYearLastDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * 获取指定日期相差几个月的第一天
	 * 
	 * @param date
	 * @param months
	 *            月数差，可为负
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:11:04
	 */
	public static Date getMonthFirstDay(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期相差几个月的最后一天
	 * 
	 * @param date
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:11:04
	 */
	public static Date getMonthLastDay(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取相差指定天数的时间
	 * 
	 * @param date
	 * @param days
	 *            天数差，可为负
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:31:23
	 */
	public static Date getDateInrcByDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期时分秒的时间
	 * 
	 * @param date
	 * @param hhmmss
	 *            时分秒 "00:00:00","23:59:59"
	 * @return Date
	 * @author jiangyf
	 * @since 2016年8月17日 下午6:30:11
	 */
	public static Date getDateByHMS(Date date, String hhmmss) {
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr
					+ " " + hhmmss.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss 或 yyyy-MM-dd
	 * 
	 * @return date
	 */
	public static Date toDate(String dateStr) {
		String datetimeEx = "^\\d{4}\\-\\d{1,2}\\-\\d{1,2} \\d{2}:\\d{2}:\\d{2}$";
		String dateEx = "^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$";
		Pattern datetimePattern = Pattern.compile(datetimeEx);
		Pattern datePattern = Pattern.compile(dateEx);

		Matcher datetimeMatcher = datetimePattern.matcher(dateStr);
		Matcher dateMatcher = datePattern.matcher(dateStr);
		Date date = null;

		try {
			if (datetimeMatcher.find()) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				date = sdf.parse(dateStr);
			} else if (dateMatcher.find()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(dateStr);
			} else {
				throw new BizException(BizExpDictionary.DATEPATTERNERROR);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BizException(BizExpDictionary.DATEFORMATERROR);
		}
		return date;
	}

	/**
	 * 日期和天数加法计算
	 * 
	 * @param date
	 *            日期
	 * @param days
	 *            天数
	 * @return Date
	 */
	public static Date calculateDate(Date date, Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return toDate(format.format(date));
	}
	
	/**
	 * 获取日期（月/日）
	 * 
	 * @param date
	 *            当前日期
	 * @param gap
	 *            时间差，天数
	 * @return String
	 */
	public static String getMonthDay(Date date, int gap) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, gap);
		return (calendar.get(Calendar.MONTH) + 1) + "月"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "号";
	}

	public static void main(String[] args) throws ParseException {
		// Date nowDate = new Date();
		// System.out.println(DateUtils.getLast(nowDate, 0));
		// System.out.println(DateUtils.getLast(nowDate, 1));
		// System.out.println(DateUtils.getLast(nowDate, 2));

		// System.out.println(getMonthFirstDay(nowDate, -1));
		// System.out.println(getMonthLastDay(nowDate, 1));
		// System.out.println(getDateInrcByDays(nowDate, -1));
		// System.out.println(getDateByHMS(nowDate, "23:59:59"));
		//
		// System.out.println(getCurrYearFirstDay()+"---"+getCurrYearLastDay());
		// System.out.println(getYearFirstDay(2018)+"---"+getYearLastDay(2018));

	}

}
