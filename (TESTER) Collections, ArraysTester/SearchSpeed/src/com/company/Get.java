package com.company;

import java.text.DecimalFormat;
import java.util.*;

public class Get {

    static Scanner scanner = new Scanner(System.in);
    static long start;
    static double duration;
    static String input;
    static String letters = "ABCEHKMOPTXY";
    static DecimalFormat numberFormatter = new DecimalFormat("000");
    static DecimalFormat regionFormatter = new DecimalFormat("00");
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        System.out.printf("Доступ по индексу %n%n");

        fillArray();

        List<String> linkedList = new LinkedList<>(list);
        Set<String> hashSet = new HashSet<>(list);
        Set<String> treeSet = new TreeSet<>(list);
        String[] array = new String[list.size()]; // String[]
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        boolean containsInput = false;

        int randomNumber = (int) (Math.random() * 3438710);

        // ТЕСТЫ
        start = System.nanoTime();
        System.out.println(array[randomNumber]);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Доступ к String[]: доступ занял %.2f ms%n", duration);


        start = System.nanoTime();
        System.out.println(list.get(randomNumber));
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Доступ к List: доступ занял %.2f ms%n", duration);


        start = System.nanoTime();
        System.out.println(linkedList.get(randomNumber));
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Доступ к LinkedList: доступ занял %.2f ms%n", duration);


        start = System.nanoTime();
        for (String item : hashSet) {
            if (item.equals(list.get(randomNumber))) {
                System.out.println(item);
            }
        }
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Доступ к HashSet: доступ занял %.2f ms%n", duration);


        start = System.nanoTime();
        for (String item : treeSet) {
            if (item.equals(list.get(randomNumber))) {
                System.out.println(item);
            }
        }
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Доступ к TreeSet: доступ занял %.2f ms%n", duration);





























    }

    private static void fillArray() {

        for (int i = 0; i < letters.length(); i++) {
            String char1st = Character.toString(letters.charAt(i));
            for (int j = 0; j < 1000; j += 111) {
                String digits = numberFormatter.format(j);
                for (int k = 0; k < letters.length(); k++) {
                    String char2nd = Character.toString(letters.charAt(k));
                    for (int l = 0; l < letters.length(); l++) {
                        String char3rd = Character.toString(letters.charAt(l));
                        for (int m = 1; m < 200; m++) {
                            String regions = regionFormatter.format(m);
                            String finalNumber = char1st + digits + char2nd + char3rd + regions;
                            list.add(finalNumber);
                        }
                    }
                }
            }
        }
    }
}
