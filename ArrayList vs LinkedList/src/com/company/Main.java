package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static long start;
    static double result;

    public static void main(String[] args) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        List<Integer> list = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();


        // ArrayList
        // ADD
       /* start = System.nanoTime();
        for (int i = 0; i < 100000; i++) { // 10 ms (1373 ms)
            list.add(0, i);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);*/

        //GET
        /*long start1 = System.nanoTime(); // 500 Супер быстро в сравнении с Линкд Листом
        for (int i = 0; i < 100000; i++) { //
            System.out.println(list.get(i));
        }
        double result1 = (double) (System.nanoTime() - start1) / 1000000;
        System.out.println(result1);*/

        //REMOVE
        /*start = System.nanoTime();
        for (int i = 0; i < 100000; i++) { // 10 ms
            list.remove(list.size() - 1);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);
        System.out.println(list);*/

        // LinkedList
        // ADD
        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) { // 10 ms (15 ms) Супер быстрее если добавлять на нулевой, с конца одинаково
            linkedList.add(0, i);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);

        //GET
        /*long start = System.nanoTime(); // 9388 ms
        for (int i = 0; i < 100000; i++) {
            System.out.println(linkedList.get(i));
        }
        double result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);*/

        //REMOVE
        /*start = System.nanoTime();
        for (int i = 0; i < 100000; i++) { // 8 ms
            linkedList.remove(linkedList.size() - 1);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);
        System.out.println(list);*/
    }
}

