package com.guoenbo.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leeandy007 on 16/9/24.
 */

public class DateUtil {

    private static SimpleDateFormat dateFormat;

    /**
     * 字符串转换为日期格式
     *
     * @param date
     *            字符串
     * @param sFormate
     *            "yyyy-MM-dd HH:mm:ss"
     * @return Date
     */
    public static Date getStringToDate(String date, String sFormate) {
        try {
            dateFormat = new SimpleDateFormat(sFormate);
            return dateFormat.parse(date);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * 日期转换为字符串格式
     *
     * @param date
     *            Date类型
     * @param sFormate
     *            : "yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    public static String getDateToString(Date date, String sFormate) {
        dateFormat = new SimpleDateFormat(sFormate);
        if (date == null) {
            return null;
        } else {
            return dateFormat.format(date);
        }
    }

}
