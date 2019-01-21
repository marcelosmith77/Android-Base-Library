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

    public static String anoMesDia(Date date) {
        if (date == null)
            return "";

        return (String) DateFormat.format("yyyyMMdd", date);
    }

    public static Date primeiroDiaMes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static String diaMes(Date date) {
        if (date == null)
            return "";

        return (String) DateFormat.format("dd-MMM", date);
    }

    public static String diaDaSemana(long date, boolean uppercase) {
        String diaSemana = (String) DateFormat.format("E ", new Date(date));

        if (uppercase)
            return diaSemana.toUpperCase();

        return diaSemana;
    }


    public static String shortDate(Date date) {
        if (date == null)
            return "";

        return (String) DateFormat.format("dd-MM", date);
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

    public static boolean isDateInCurrentYear(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return year == targetYear;
    }

    public static int monthDif(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetMonth = targetCalendar.get(Calendar.MONTH);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        if (year == targetYear)
            return month - targetMonth;

        return (month - targetMonth) + ((year - targetYear) * 12);
    }

    public static int daysDiff(Date date) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal2.setTime(date);

        return daysBetween(cal1, cal2);
    }

    public static int weekDif(Date date) {
        Calendar cal = Calendar.getInstance();

        int weekToday = cal.get(Calendar.WEEK_OF_YEAR);
        int yearToday = cal.get(Calendar.YEAR);
        int monthToday = cal.get(Calendar.MONTH);

        cal.setTime(date);
        int weekDate = cal.get(Calendar.WEEK_OF_YEAR);
        int weekYear = cal.get(Calendar.YEAR);
        int weekMonth = cal.get(Calendar.MONTH);

        if (monthToday == 11 && weekToday == 1  && yearToday == weekYear) {
            weekToday = 53;
        }

        if (weekMonth == 11 && weekDate == 1 && yearToday == weekYear) {
            weekDate = 53;
        } else if (weekToday == 1 && weekMonth == 11 && weekDate == 1 && yearToday == weekYear + 1) {
            return 0;
        } else if (weekToday == 1 && weekMonth == 11 && weekDate == 52 && yearToday == weekYear + 1) {
            return 1;
        }

        if (yearToday == weekYear)
            return weekDate - weekToday;

        return ((yearToday - weekYear) * 53) - weekDate + weekToday; // FIXED YEAR WEEKS

    }

    public static int daysBetween(Calendar day1, Calendar day2) {
        Calendar dayOne = (Calendar) day1.clone(),
                dayTwo = (Calendar) day2.clone();

        if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
            return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
        } else {
            if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = dayOne;
                dayOne = dayTwo;
                dayTwo = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

            while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
                dayOne.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays;
        }
    }

    public static String valueOf(Date value) {
        return String.valueOf(value.getTime());
    }

    public static Date valueOf(String value) {
        return new Date(Long.valueOf(value));
    }

}
