package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String dateToString(Date date, String formatString){
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(date);
    }

    public static Date stringToDate(String dateString, String formatString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.parse(dateString);
    }

    public static long dateToLong(Date date){
        return date.getTime();
    }

    public static Date longToDate(long dateLong){
        return new Date(dateLong);
    }

    public static long stringToLong (String dateString, String formatString) throws ParseException {
        return dateToLong(stringToDate(dateString, formatString));
    }

    public static String longToString(long dateLong, String formatString){
        return dateToString(longToDate(dateLong), formatString);
    }

    public static void main(String[] args){
        try {
            System.out.println("2016-11-27 15:20:11");
            System.out.println(stringToLong("2016-11-27 15:20:11", "yyyy-MM-dd HH:mm:ss"));
            System.out.println(longToString(stringToLong("2016-11-27 15:20:11", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
