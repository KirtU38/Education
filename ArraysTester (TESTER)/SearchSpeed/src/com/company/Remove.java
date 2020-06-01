package com.company;

import java.text.DecimalFormat;
import java.util.*;

public class Remove {

    static Scanner scanner = new Scanner(System.in);
    static long start;
    static double duration;
    static String input;
    static String letters = "ABCEHKMOPTXY";
    static DecimalFormat numberFormatter = new DecimalFormat("000");
    static DecimalFormat regionFormatter = new DecimalFormat("00");
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        System.out.printf("Удаление методом .remove() %n%n");

        fillArray();

        List<String> linkedList = new LinkedList<>(list);
        Set<String> hashSet = new HashSet<>(list);
        Set<String> treeSet = new TreeSet<>(list);
        String[] array = new String[list.size()]; // String[]
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }



        // ТЕСТЫ
        start = System.nanoTime();
        array[0] = "";
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из String[] с начала: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        array[1700000] = "";
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из String[] с середины: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        array[array.length -1 ] = "";
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из String[] с конца: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        list.remove(0);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из List с начала: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        list.remove(1700000);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из List с середины: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        list.remove(list.size() - 1);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из List с конца: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        linkedList.remove(0);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из LinkedList с начала: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        linkedList.remove(1700000);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из LinkedList с середины: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        linkedList.remove(list.size() - 1);
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из LinkedList с конца: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        hashSet.remove("A000AA02");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из HashSet с начала: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        hashSet.remove("K999EX145");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из HashSet с середины: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        hashSet.remove("Y999YY198");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из HashSet с конца: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        treeSet.remove("A000AA02");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из TreeSet с начала: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        treeSet.remove("K999EX145");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из TreeSet с середины: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        treeSet.remove("Y999YY198");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Удаление из TreeSet с конца: заняло %.2f ms%n", duration);



















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
