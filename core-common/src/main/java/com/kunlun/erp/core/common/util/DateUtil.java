package com.kunlun.erp.core.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description 日期处理工具
 * @Author Jm.zhang
 * @Date 2019/11/14 11:24
 * @Version 1.0
 **/
public class DateUtil {

    //时间格式
    private static final String format_date = "yyyy-MM-dd";
    private static final String format_date_str = "yyyyMMdd";
    private static final String format_datetime = "yyyy-MM-dd HH:mm:ss";
    private static final String format_datetime_str = "yyyyMMddHHmmss";
    private static final String format_datetime_detail = "yyyy-MM-dd HH:mm:ss:SSS";
    private static final String format_datetime_detail_str = "yyyyMMddHHmmssSSS";
    public static SimpleDateFormat FORMATTER_DATE = new SimpleDateFormat(DateUtil.format_date);
    public static SimpleDateFormat FORMATTER_DATE_STR = new SimpleDateFormat(DateUtil.format_date_str);
    public static SimpleDateFormat FORMATTER_DATE_TIME = new SimpleDateFormat(DateUtil.format_datetime);
    public static SimpleDateFormat FORMATTER_DATE_TIME_STR = new SimpleDateFormat(DateUtil.format_datetime_str);
    public static SimpleDateFormat FORMATTER_DATE_TIME_DETAIL = new SimpleDateFormat(DateUtil.format_datetime_detail);
    public static SimpleDateFormat FORMATTER_DATE_TIME_DETAIL_STR = new SimpleDateFormat(DateUtil.format_datetime_detail_str);

    public static void main(String[] args) {
        String birthday= "1949-10-01";
        System.out.println(DateUtil.getAgeByBirthday(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,birthday)));



    }

    /**
     * 计算 几天后的日期
     * @param date 被操作日期
     * @param diff_day  向后几天
     * @return Date 目标日期
     */
    public static Date getDateAfter(Date date, int diff_day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + diff_day);
        return calendar.getTime();
    }

    /**
     * 计算 几分钟后的时间
     * @param date 被操作日期
     * @param min  向后几分钟
     * @return Date 目标日期
     */
    public static Date getDateAfterMinute(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + min);
        return calendar.getTime();
    }
    /**
     * 根据日期 获取年龄
     * @param birthday 生日
     * @return 年龄
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;

        if (monthNow == monthBirth) {
            //同月
            if (dayOfMonthNow < dayOfMonthBirth){
                //没过生日
                age--;
            }
        }else if(monthNow < monthBirth){
            //没过生日
            age --;
        }
        return age;
    }

    /**
     * 两个时间相差几天
     * @param pre_date
     * @param suf_date
     * @return
     */
    public static int getDiffDayBetweenDate(Date pre_date,Date suf_date){
        Calendar pre_cal = Calendar.getInstance();
        pre_cal.setTime(pre_date);
        pre_cal.set(Calendar.HOUR_OF_DAY,0);
        pre_cal.set(Calendar.MINUTE,0);
        pre_cal.set(Calendar.SECOND,0);

        Calendar suf_cal = Calendar.getInstance();
        suf_cal.setTime(suf_date);
        suf_cal.set(Calendar.HOUR_OF_DAY,0);
        suf_cal.set(Calendar.MINUTE,0);
        suf_cal.set(Calendar.SECOND,0);
        int days = ((int) (suf_cal.getTime().getTime() / 1000) - (int) (pre_cal.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 功能描述:获取当前时间
     *
     * @return date
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    public static Date str2Date(String str) throws ParseException {
        return FORMATTER_DATE.parse(str);
    }

    public static Date strToDateByFormat(SimpleDateFormat format, String str) {
        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToStringByFormat(SimpleDateFormat format, Date d) {
        return format.format(d);
    }

    public static String millisToStringByFormat(SimpleDateFormat format, Long d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d);
        return format.format(calendar.getTime());
    }


    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

}
