package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean firstIteration = true;
        double num = 0;

        for (; ; ) {

            if (firstIteration) {
                num = scanner.nextDouble();
            }
            String operator = scanner.next();

            if (operator.equals("+")) {
                num = num + scanner.nextDouble();
            }
            if (operator.equals("-")) {
                num = num - scanner.nextDouble();
            }
            if (operator.equals("*")) {
                num = num * scanner.nextDouble();
            }
            if (operator.equals("/")) {
                num = num / scanner.nextDouble();
            }
            System.out.println(num);
            firstIteration = false;
        }
    }
}


