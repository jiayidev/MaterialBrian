package dev.brian.materialbrian.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at 2017/10/23
 * Description: 时间管理工具类
 */
public class TimeUtils {
    public static String getTimeFromStamp(long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(timeStamp));
    }

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(Calendar.getInstance().getTime());
    }

    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String parseTime(String complexTime) {
        return complexTime;
    }
}
