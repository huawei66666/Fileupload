package com.huawei.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    public static int getYear(Date date) {
        return Integer.parseInt(formatDate(date, "yyyy"));
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 时间戳转化为jsp页面字符串
     * 转换格式（yyyy-MM-dd HH:mm:ss）
     * @param timeMillis
     * @return
     */
    public static String formatTimestamp(Long timeMillis) {
        Date date = new Date(timeMillis);
		return formatDateTime(date);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获得 剩余天数
     *
     * @param endDate
     * @return
     */
    public static int getSurplusDay(Date endDate) {
        if (endDate == null)
            return -1;
        long beforeTime = new Date().getTime();
        long afterTime = endDate.getTime();
        return (int) ((afterTime - beforeTime) / (1000 * 60 * 60 * 24));
    }


    /**
     * 获得 耗时
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getElapsedTime(Date startDate, Date endDate) {
        if (startDate == null) {
            return "";
        }
        if (endDate == null) {
            endDate = new Date();
        }
        //时间差
        long diff = endDate.getTime() - startDate.getTime();
        return showTime(Long.valueOf(diff / 1000).intValue());
    }

    /**
     * 根据 秒钟 返回时间
     *
     * @param second 秒钟
     * @return String
     */
    public static final String showTime(Integer second) {
        int seconds = 60;
        int hourSeconds = 60 * 60;
        int daySeconds = 60 * 60 * 24;
        if (second < seconds)
            return second + "秒";
        if (second < hourSeconds) {
            int remain = second % seconds;
            if (remain == 0) {
                return second / hourSeconds + "分钟";
            } else {
                String remainTime = showTime(remain);
                return second / seconds + "分" + remainTime;
            }
        } else if (second < daySeconds) {
            int remain = second % hourSeconds;
            if (remain == 0) {
                return second / hourSeconds + "小时";
            } else {
                String remainTime = showTime(remain);
                int hour = second / hourSeconds;
                return hour + "时" + remainTime;
            }
        } else {
            int remain = second % daySeconds;
            if (remain == 0) {
                return second / daySeconds + "天";
            } else {
                String remainTime = showTime(remain);
                return second / daySeconds + "天" + remainTime;
            }
        }
    }

    /**
     * 获得 当前时间
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获得 今天开始时间
     * @return
     */
    public static Date getToDayStart() {
        return getDayStart(new Date());
    }

    /**
     * 获得 今天结束时间
     * @return
     */
    public static Date getToDayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 获取 上个月开始时间
     * @return
     */
    public static Date getLastMonthStart() {
        Calendar cale = Calendar.getInstance();
        cale.setTime(getLastMonthEnd());
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        return cale.getTime();
    }

    /**
     * 获取 上个月结束时间
     * @return
     */
    public static Date getLastMonthEnd() {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        return cale.getTime();
    }


    /**
     * 获取 上周开始时间
     * @return
     */
    public static Date getLastWeekStart() {
        Calendar cale = Calendar.getInstance();
        cale.setTime(getLastWeekEnd());
        cale.add(Calendar.DAY_OF_WEEK, -6);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        return cale.getTime();
    }

    /**
     * 获取 上周结束时间
     * @return
     */
    public static Date getLastWeekEnd() {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        return cale.getTime();
    }

    /**
     * 获得 昨天 开始时间
     * @return
     */
    public static Date getYesterdayStart() {
        return getDayStart(addDays(new Date(), -1));
    }

    /**
     * 获得 昨天 结束时间
     * @return
     */
    public static Date getYesterdayEnd() {
        return getDayEnd(addDays(new Date(), -1));
    }


    /**
     * 获得 天 开始时间
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(date);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        return begin.getTime();
    }

    /**
     * 获得 天 结束时间
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(date);
        begin.set(Calendar.HOUR_OF_DAY, 23);
        begin.set(Calendar.MINUTE, 59);
        begin.set(Calendar.SECOND, 59);
        return begin.getTime();
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     * @param date 基准日期
     * @param dateField 偏移的粒度大小（小时、天、月等）{@link DateField}
     * @param offset 偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date offset(Date date, DateField dateField, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(dateField.getValue(), offset);
        return cal.getTime();
    }

    /**
     * 当前日期加上n个月
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, Integer month) {
        return offset(date, DateField.MONTH, month);
    }

    /**
     * 当前日期加上n个月
     * @param date
     * @param month
     * @param pattern 返回格式，默认为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String addMonth(Date date, Integer month, Object... pattern) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(date);
        begin.add(Calendar.MONTH, month);
        String pa = "";
        if (pattern != null && pattern.length > 0) {
            pa = pattern[0].toString();
        } else {
            pa = "yyyy-MM-dd HH:mm:ss";
        }
        return DateFormatUtils.format(begin.getTime(), pa);
    }

    public static boolean isSameYearMonth(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH));
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取系统当前时间戳
     *
     * @param pattern
     * @return
     */
    public static Long getSysTime(String pattern) {
        try {
            pattern = StringUtils.isEmpty(pattern) ? "yyyy-MM-dd HH:mm:ss" : pattern;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            return sdf.parse(sdf.format(c.getTime())).getTime();
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        Date endDate = new Date();
        long start = System.currentTimeMillis();
        String time = getElapsedTime(new Date("2016/09/28 10:10:10"), endDate);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + ", " + time);

//        System.out.println(formatDateTime(getToDayStart()));
//        System.out.println(formatDateTime(getToDayEnd()));
//        System.out.println(formatDateTime(getYesterdayStart()));

        System.out.println(formatDateTime(getLastMonthStart()));
        System.out.println(formatDateTime(getLastMonthEnd()));
        System.out.println(formatDateTime(getLastWeekStart()));
        System.out.println(formatDateTime(getLastWeekEnd()));



        double day = getSurplusDay(new Date("2016/10/17 20:10:10"));
        System.out.println(day);
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

    }
}
