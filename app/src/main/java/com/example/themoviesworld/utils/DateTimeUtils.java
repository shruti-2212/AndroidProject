package com.example.themoviesworld.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.abs;

public class DateTimeUtils {

    public static Date currentDateAndTime = new Date();

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static Date getCurrentDate() {
        return currentDateAndTime;
    }

    public static long getCurrentTime() {
        long currentTime = currentDateAndTime.getTime();
        return currentTime;
    }


    public static Date parseStringToDate(String time) {
        Date date = null;
        try {

            date = dateFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String parseDateToString(Date date) {
        return dateFormat.format(date);
    }

    public static long getTimeDifferenceinSeconds(long t1, long t2) {
        long diff;
        diff = abs(t1 - t2);
        return diff;
    }

}
