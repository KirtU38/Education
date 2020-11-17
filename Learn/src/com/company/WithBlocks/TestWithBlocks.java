package com.company.WithBlocks;

public class TestWithBlocks {

    public Integer a = 0;
    public Integer b = 0;
    public Integer c = 0;
    public Integer d = 0;
    public Integer e = 0;

    public void incrementA() {
        synchronized (a) {
            a++;
            System.out.println("a++   " + Thread.currentThread().getName());
        }
    }

    public void incrementB() {
        synchronized (b) {
            b++;
            System.out.println("b++   " + Thread.currentThread().getName());
        }
    }

    public void incrementC() {
        synchronized (c) {
            c++;
            System.out.println("c++   " + Thread.currentThread().getName());
        }
    }

    public void incrementD() {
        synchronized (d) {
            d++;
            System.out.println("d++   " + Thread.currentThread().getName());
        }
    }

    public void incrementE() {
        synchronized (e) {
            e++;
            System.out.println("e++   " + Thread.currentThread().getName());
        }
    }

    public void incrementAll() {
        synchronized (a) {
            a++;
            System.out.println("a++   " + Thread.currentThread().getName());
            synchronized (b) {
                b++;
                System.out.println("b++   " + Thread.currentThread().getName());
                synchronized (c) {
                    c++;
                    System.out.println("c++   " + Thread.currentThread().getName());
                    synchronized (d) {
                        d++;
                        System.out.println("d++   " + Thread.currentThread().getName());
                        synchronized (e) {
                            e++;
                            System.out.println("e++   " + Thread.currentThread().getName());
                        }
                    }
                }
            }

        }
    }
}
