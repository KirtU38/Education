package com.company;

import java.text.DecimalFormat;
import java.util.*;

public class Add {

    static Scanner scanner = new Scanner(System.in);
    static long start;
    static double duration;
    static String input;
    static String letters = "ABCEHKMOPTXY";
    static DecimalFormat numberFormatter = new DecimalFormat("000");
    static DecimalFormat regionFormatter = new DecimalFormat("00");
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        System.out.printf("Добавление методом .add() %n%n");

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
        array[0] = "X999XX99";
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к String[] в начало: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        array[1700000] = "X999XX99";
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к String[] в середину: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        array[array.length -1 ] = "X999XX99";
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к String[] в конец: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        list.add(0,"X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к List в начало: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        list.add(1700000,"X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к List в середину: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        list.add(list.size() - 1,"X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к List в конец: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        linkedList.add(0,"X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к LinkedList в начало: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        linkedList.add(1700000,"X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к LinkedList в середину: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        linkedList.add(list.size() - 1,"X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к LinkedList в конец: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        hashSet.add("X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к HashSet в конец: заняло %.2f ms%n", duration);


        start = System.nanoTime();
        treeSet.add("X999XX99");
        duration = (double) (System.nanoTime() - start) / 1e+6;
        System.out.printf("Добавление к TreeSet: заняло %.2f ms%n", duration);
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
