package com.company.Regular;

public class Test {

    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;

    public synchronized void incrementA() {
        if (a > b || a > c || a > d || a > e) {
            try {
                System.out.println("A wait()");
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (a <= b && a <= c && a <= d && a <= e) {
            a++;
            System.out.println("a++   " + Thread.currentThread().getName());
            notify();
        }
    }

    public synchronized void incrementB() {
        if (b > a || b > c || b > d || b > e) {
            try {
                System.out.println("B wait()");
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (b <= a && b <= c && b <= d && b <= e) {
            b++;
            System.out.println("b++   " + Thread.currentThread().getName());
            notify();
        }
    }

    public synchronized void incrementC() {
        if (c > a || c > b || c > d || c > e) {
            try {
                System.out.println("C wait()");
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (c <= a && c <= b && c <= d && c <= e) {
            c++;
            System.out.println("c++   " + Thread.currentThread().getName());
            notify();
        }
    }

    public synchronized void incrementD() {
        if (d > a || d > b || d > c || d > e) {
            try {
                System.out.println("D wait()");
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (d <= a && d <= b && d <= c && d <= e) {
            d++;
            System.out.println("d++   " + Thread.currentThread().getName());
            notify();
        }
    }

    public synchronized void incrementE() {
        if (e > a || e > b || e > c || e > d) {
            try {
                System.out.println("E wait()");
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (e <= a && e <= b && e <= c && e <= d) {
            e++;
            System.out.println("e++   " + Thread.currentThread().getName());
            notify();
        }
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
