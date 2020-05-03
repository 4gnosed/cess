package cn.edu.cess.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/26
 * @Description
 */
public class DateTimeUtils {
    private static final String defaultDateFormatStr = "yyyy-MM-dd";// 系统默认的格式化字符串
    private static final String defaultTimeFormatStr = "yyyy-MM-dd HH:mm:ss";// 系统默认的格式化字符串

    /**
     * 日期转字符串
     */
    public static String dateToString(Date date, String formatStr) {
        if ("".equals(formatStr) || null == formatStr) {
            formatStr = defaultTimeFormatStr;
        }
        DateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 字符串转换到时间格式，自定义日期格式
     */
    public static Date stringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 取得系统时间，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getSystemTime() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat(defaultTimeFormatStr);
        strTime = df.format(new Date());
        return strTime;
    }

    /**
     * 取得系统日期，格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getSystemDate() {
        String strDate = "";
        SimpleDateFormat df = new SimpleDateFormat(defaultDateFormatStr);
        strDate = df.format(new Date());
        return strDate;
    }

    /**
     * 取得系统日期，格式为yyyy-MM-dd
     *
     * @return
     */
    public static Date getCurrentDate() {
        String systemDate = DateTimeUtils.getSystemDate();
        return stringToDate(systemDate, defaultDateFormatStr);
    }
    /**
     * 取得系统日期，格式为yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getCurrentTime() {
        String systemTime = DateTimeUtils.getSystemTime();
        return stringToDate(systemTime, defaultTimeFormatStr);
    }

    /**
     * 取得系统时间，日期格式为yyyyMMddHHmmss
     */
    public static String getShortSystemTime() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        strTime = df.format(new Date());
        return strTime;
    }

    /**
     * 取得系统短日期，yyyyMMdd
     */
    public static String getShortSystemDate() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        strTime = df.format(new Date());
        return strTime;
    }

    /**
     * 系统时间加减，自定义日期格式
     */
    public static String getOperaDate(String date, int dayNum, String formatStr) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();
        assert dt != null;
        gc.setTime(dt);
        gc.add(Calendar.DATE, dayNum);
        return df.format(gc.getTime());
    }

    /**
     * 系统时间加减，默认日期格式
     */
    public static String getOperaDate(String date, int dayNum) {
        return getOperaDate(date, dayNum, defaultDateFormatStr);
    }

    /**
     * 系统月份加减，自定义日期格式
     */
    public static String getOperaMonth(String date, int monthNum, String formatStr) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();
        assert dt != null;
        gc.setTime(dt);
        gc.add(Calendar.MONTH, monthNum);
        return df.format(gc.getTime());
    }

    /**
     * 系统月份加减，默认日期格式
     */
    public static String getOperaMonth(String date, int monthNum) {
        return getOperaMonth(date, monthNum, defaultDateFormatStr);
    }

    /**
     * 取得两个日期的时间差（天数），两个都是字符串，格式自定义
     */
    public static int getDateDifference(String date1, String date2, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos1 = new ParsePosition(0);
        Date dt1 = formatter.parse(date1, pos);
        Date dt2 = formatter.parse(date2, pos1);
        return (int) (dt2.getTime() - dt1.getTime()) / (3600 * 24 * 1000);
    }

    /**
     * 取得两个日期的时间差（天数），两个都是字符串，格式为 yyyy-MM-dd
     */
    public static int getDateDifference(String date1, String date2) {
        return getDateDifference(date1, date2, defaultDateFormatStr);
    }

    /**
     * 取得两个日期的时间差（小时）,两个都是日期型
     */
    public static int getHourDifference(Date date1, Date date2) {
        return (int) (date2.getTime() - date1.getTime()) / (3600 * 1000);
    }

    /**
     * 取得两个日期的月份差
     */
    public static int getMonthDifference(String date1, String date2, String formatStr) {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 取得两个日期的月份差
     */
    public static int getMonthDifference(String date1, String date2) {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormatStr);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return result;
    }

    /**
     * 取得当月最后一天
     */
    public static String getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
        cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
        return new SimpleDateFormat(defaultDateFormatStr).format(cal.getTime());
    }

    /**
     * 取得当月第一天
     */
    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        return new SimpleDateFormat(defaultDateFormatStr).format(cal.getTime());// 获得月初是几号
    }

    /**
     * 取得上个月的第一天
     */
    public static String getFirstDayOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, -1);// 月份减一，得到上个月的一号
        return new SimpleDateFormat(defaultDateFormatStr).format(cal.getTime());// 获得月初是几号
    }

    /**
     * 取得下个月的最后一天
     */
    public static String getLastDayOfNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, 2);// 月份加一，得到下下个月的一号
        cal.add(Calendar.DATE, -1);// 下下一个月减一为下个月最后一天
        return new SimpleDateFormat(defaultDateFormatStr).format(cal.getTime());// 获得月末是几号
    }

    /**
     * 取得当月最后一天
     */
    public static String getLastDayOfMonth(String date) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(defaultDateFormatStr);
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        assert dt != null;
        cal.setTime(dt);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
        cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
        return df.format(cal.getTime());// 获得月末是几号
    }

    /**
     * 获取某个时间段的所有天数集合(包含起始日期与终止日期)
     */
    public static List<String> getDayList(String starDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat(defaultDateFormatStr);
        List<String> dayList = new ArrayList<String>();
        if (starDate.equals(endDate)) {
            dayList.add(starDate);
        } else if (starDate.compareTo(endDate) < 0) {
            while (starDate.compareTo(endDate) <= 0) {
                dayList.add(starDate);
                long l = stringToDate(starDate, "yyyy-MM-dd").getTime();
                starDate = format.format(l + 3600 * 24 * 1000);
            }
        } else {
            dayList.add(endDate);
        }
        return dayList;
    }
}
