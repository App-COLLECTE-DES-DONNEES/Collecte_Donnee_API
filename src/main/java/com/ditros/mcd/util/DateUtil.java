package com.ditros.mcd.util;

import org.apache.tomcat.jni.Local;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDateTime DateFromText(String pattern, String date){
        if(date==null) return null;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);

    }

    public static LocalDate DateDayFromText(String pattern, String date){
        if(date==null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);

    }
    public static String textFromDate(String pattern, LocalDateTime date){
        if(date==null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);

    }
    public static int findAge(int birthYear){
        return LocalDateTime.now().getYear()-birthYear;
    }
    public static int findAge(String birthdate){
        if(birthdate ==null) return 0;
        LocalDateTime date = DateDayFromText("dd/MM/yyyy", birthdate).atStartOfDay();
        return LocalDateTime.now().getYear()-date.getYear();
    }
}
