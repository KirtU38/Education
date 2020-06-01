package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    static long start;
    static double result;

    public static void main(String[] args) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        Set<Integer> set = new HashSet<>();
        Set<Integer> tree = new TreeSet<>();

        // HashSet
        // ADD
        /*start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) { // 250 ms Быстрее в добавлении потому что не сортирует
            set.add(i);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);*/

        // CONTAINS
       /* start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) { // 12 ms
            set.contains(i);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);*/

        // CONTAINS 1 value
        /*start = System.nanoTime();
        System.out.println(set.contains(3));
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);*/





        //TreeSet
        //ADD
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) { //400 ms
            tree.add(i);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);

        // CONTAINS
        /*start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) { // 17 ms
            tree.contains(i);
        }
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);*/

        // CONTAINS 1 value
        start = System.nanoTime();
        System.out.println(tree.contains(500000));
        result = (double) (System.nanoTime() - start) / 1000000;
        System.out.println(result);
    }
}

