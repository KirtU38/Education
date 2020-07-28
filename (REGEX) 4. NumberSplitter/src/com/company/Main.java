package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String numberStr = input.replaceAll("\\D", "");
        String pattern = "(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})";
        String formattedPhone = numberStr.replaceAll(pattern, "+$1 ($2) $3-$4-$5");

        System.out.println(formattedPhone);
    }
}
