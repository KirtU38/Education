package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("d/M/y EEEE");
        Calendar calendar = Calendar.getInstance();

        for (int i = 1994, j = 0; i < 2021; i++, j++) {
            calendar.set(i, Calendar.DECEMBER, 12);
            System.out.println(j + " - " + dateFormat.format(calendar.getTime()));
        }
    }
}

