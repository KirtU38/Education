package com.company;
import com.company.Regular.*;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Потоки на один обьект класса со всеми synchronized методами
        Test test = new Test();
        long start = System.currentTimeMillis();

        //new Thread(new RunnerAll(test, start), "All").start();
        new Thread(new RunnerA(test, start), "A-Runner").start();
        new Thread(new RunnerB(test, start), "B-Runner").start();
        new Thread(new RunnerC(test, start), "C-Runner").start();
        new Thread(new RunnerD(test, start), "D-Runner").start();
        new Thread(new RunnerE(test, start), "E-Runner").start();

        scanner.nextLine();
        System.out.printf(" a-%d%n b-%d%n c-%d%n d-%d%n e-%d%n", test.a, test.b, test.c, test.d, test.e);

    }
}




