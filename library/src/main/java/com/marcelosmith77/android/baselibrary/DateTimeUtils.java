package com.marcelosmith77.android.baselibrary;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static String mes(Date date) {
        return (String) DateFormat.format("MMMM", date);
    }

    public static String hora(Date date) {
        if (date == null)
            return "";

        return (String) DateFormat.format("HH:mm", date);
    }

    public static String data(Date date) {
        if (date == null)
            return "";

        return (String) DateFormat.format("ddMMyyyy", date);
    }


    public static Date addMes(Date date, int meses) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, meses);

        return cal.getTime();
    }

    public static Date addDay(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal;
    }

    public static Date getDataUltimoDiaMes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

        return cal.getTime();
    }

    public static Date clearTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //cal.set(Calendar.ZONE_OFFSET, 0);
        return cal.getTime();
    }

    public static Date setMidnight(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        //cal.set(Calendar.ZONE_OFFSET, 0);
        return cal.getTime();
    }

}
