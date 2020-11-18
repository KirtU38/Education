package com.company.Static;

public class TestStatic {

    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;

    public static synchronized void incrementA() {
        a++;
        System.out.println("a++   " + Thread.currentThread().getName());
    }

    public static synchronized void incrementB() {
        b++;
        System.out.println("b++   " + Thread.currentThread().getName());
    }

    public static synchronized void incrementC() {
        c++;
        System.out.println("c++   " + Thread.currentThread().getName());
    }

    public static synchronized void incrementD() {
        d++;
        System.out.println("d++   " + Thread.currentThread().getName());
    }

    public static synchronized void incrementE() {
        e++;
        System.out.println("e++   " + Thread.currentThread().getName());
    }

    public static synchronized void incrementAll() {
        a++;
        System.out.println("a++   " + Thread.currentThread().getName());
        b++;
        System.out.println("b++   " + Thread.currentThread().getName());
        c++;
        System.out.println("c++   " + Thread.currentThread().getName());
        d++;
        System.out.println("d++   " + Thread.currentThread().getName());
        e++;
        System.out.println("e++   " + Thread.currentThread().getName());
    }

}
