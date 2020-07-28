package com.company;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        Calendar gregorian = new GregorianCalendar();

        gregorian.setTimeZone(TimeZone.getTimeZone("UTC"));
        gregorian.set(Calendar.MONTH, 1);

        System.out.println(calendar.getTime() + " - Calendar");
        System.out.println(gregorian.getTime() + " - Gregorian");

        System.out.println(gregorian.get(Calendar.YEAR) + " - year");
        System.out.println(gregorian.get(Calendar.MONTH) + " - month");
        System.out.println(gregorian.get(Calendar.DATE) + " - date");
        System.out.println(gregorian.get(Calendar.HOUR) + " - hour");
        System.out.println(gregorian.get(Calendar.WEEK_OF_YEAR) + " - week");

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

        System.out.println(dateFormat.format(gregorian.getTime()));

    }
}

