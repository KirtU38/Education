package com.company.Regular;

public class Test {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public synchronized void incrementA() {
        a++;
        System.out.println("a++   " + Thread.currentThread().getName());
    }

    public synchronized void incrementB() {
        b++;
        System.out.println("b++   " + Thread.currentThread().getName());
    }

    public synchronized void incrementC() {
        c++;
        System.out.println("c++   " + Thread.currentThread().getName());
    }

    public synchronized void incrementD() {
        d++;
        System.out.println("d++   " + Thread.currentThread().getName());
    }

    public synchronized void incrementE() {
        e++;
        System.out.println("e++   " + Thread.currentThread().getName());
    }

    public synchronized void incrementAll() {
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
