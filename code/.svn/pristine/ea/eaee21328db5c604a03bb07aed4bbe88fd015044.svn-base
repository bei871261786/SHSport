package shlottery.gov.cn.lotterycenter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateUtils {
    /**
     * 将long得到-- 小时:分
     *
     * @param lefttime
     * @return 小时:分
     */
    public static String formatTimeSimple(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 获取时间:月日
     *
     * @param lefttime
     * @return
     */
    public static String formatMonthDay(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 将long得到天 小时:分
     *
     * @param lefttime
     * @return 小时:分
     */
    public static String getDayHourM(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }


    /**
     * 将long得到-- 小时
     *
     * @param lefttime
     * @return 小时
     */
    public static String getHour(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 将long得到-- 分钟
     *
     * @param lefttime
     * @return 分钟
     */
    public static String getMinute(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    public static String formatTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    public static String formatMMSSTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日 小时:分钟
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟
     */
    public static String formatDateAndTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到:月-日 小时:分钟
     *
     * @param lefttime
     * @return 月-日 小时:分钟
     */
    public static String formatSimpleDateAndTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 月-日 小时:分钟
     *
     * @param lefttime
     * @return 月-日 小时:分钟
     */
    public static String getPl5DateAndTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日 小时:分钟:秒
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟:秒
     */
    public static String formatDateAndTimeS(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime.replaceAll("\\D", "");
    }

    /**
     * 得到: 年月日小时分钟秒
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟:秒
     */
    public static String formatDateformatTimeSimple(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 月-日
     *
     * @param lefttime
     * @return 月-日
     */
    public static String formatDateNoYear(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd ", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 小时:分钟:秒
     *
     * @param lefttime
     * @return 小时:分钟:秒
     */
    public static String formatDateHmm(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日
     *
     * @param lefttime
     * @return 年-月-日
     */
    public static String formatDate(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日
     *
     * @param lefttime
     * @return 年-月-日
     */
    public static String formatDate2(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年月日
     *
     * @param lefttime
     * @return 年月日
     */
    public static String formatDateNoLine(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 字符串转为long
     *
     * @param time     字符串时间,注意:格式要与template定义的一样
     * @param template 要格式化的格式:如time为09:21:12那么template为"HH:mm:ss"
     * @return long
     */
    public static long formatToLong(String time, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        try {
            Date d = sdf.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            long l = c.getTimeInMillis();
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到年份
     *
     * @param lefttime
     * @return 得到年份
     */
    public static int formatYear(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        int i = Integer.parseInt(sDateTime);
        return i;
    }

    /**
     * 得到月份
     *
     * @param lefttime
     * @return 得到月份
     */
    public static int formatMonth(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        int i = Integer.parseInt(sDateTime);
        return i;
    }

    /**
     * 上半场时间计算
     *
     * @method: formatLiveScoreMatchStatus
     * @return: String
     */
    public static int formatFirstHalfLiveScoreMatchStatus(Date now, Long begin) {
        long currentTime = now.getTime();
        // long beginTime = begin.getTime();
        long d = currentTime - begin;
        // long hour = d / (1000 * 60 * 60);
        long m = d / (1000 * 60);
        // int m = (int) ((d - hour * 1000 * 60 * 60) / (60 * 1000));
        // int between = (int) (((now.getTime() - begin.getTime()) / 1000));
        // int nDay = between / (24 * 60 * 60);
        // int nHour = (between - nDay * 24 * 60 * 60) / (60 * 60);
        // int nMinute = (between - nDay * 24 * 60 * 60 - nHour * 60 * 60) / 60;
        // return nMinute;
        return (int) m;
    }

    /**
     * 下半场时间计算
     *
     * @method: formatSecondHalfLiveScoreMatchStatus
     * @return: int
     */
    public static int formatSecondHalfLiveScoreMatchStatus(Date now, Date begin) {
        long currentTime = now.getTime();
        long beginTime = begin.getTime();
        long d = currentTime - beginTime;
        // long hour = d / (1000 * 60 * 60);
        long m = d / (1000 * 60);
        // int m = (int) ((d - hour * 1000 * 60 * 60) / (60 * 1000));
        // int between = (int) (((now.getTime() - begin.getTime()) / 1000));
        // int nDay = between / (24 * 60 * 60);
        // int nHour = (between - nDay * 24 * 60 * 60) / (60 * 60);
        // int nMinute = (between - nDay * 24 * 60 * 60 - nHour * 60 * 60) / 60;
        // return nMinute;
        return (int) m + 46;
    }

    /**
     * 11选5计算截止时间 返回 10:23
     *
     * @param longStart
     * @param longEnd
     * @return
     */
    public static String getTimeExpend(long longStart, long longEnd) {
        long longExpend = longEnd - longStart;  //获取时间差
        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
//        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
//        long longSeconds = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000)/(60 * 1000);   //根据时间差来计算秒数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
        long longSeconds = (longExpend) / (1000) - longMinutes * 60;   //根据时间差来计算秒数
//        Logger.e(longMinutes + "分钟" + longSeconds + "秒数");
        if (longExpend > 10 * 60 * 1000) {//如果大于十分钟
            return   "10:00";
        } else {
            if (longMinutes < 10) {
                if (longSeconds < 10 && longSeconds >= 0) {
                    return "0" + longMinutes + ":" + "0" + longSeconds;
                }
                return "0" + longMinutes + ":" + longSeconds;

            } else {
                if (longSeconds < 10 && longSeconds >= 0) {
                    return longMinutes + ":" + "0" + longSeconds;
                }
                return longMinutes + ":" + longSeconds;
            }
        }
    }
    //把yyyymmdd转成yyyy-MM-dd格式
    public static String formatDate(String str){
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sfstr;
    }
}
