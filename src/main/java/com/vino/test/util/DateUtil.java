package com.vino.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date stringToDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }

    public static Date stringToDate(String dateString) {
        return stringToDate(dateString, "dd/MM/yyyy");
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            return dateFormat.format(date);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date date) {
        return dateToString(date, "dd/MM/yyyy");
    }

    public static java.sql.Date convertToSqlDate(Date date) {
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        return sqlDate;
    }
}
