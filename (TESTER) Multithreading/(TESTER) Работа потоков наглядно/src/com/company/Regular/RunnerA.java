package com.company.Regular;

import com.company.Runner;

public class RunnerA extends Runner implements Runnable {

    Test test;
    long start;

    public RunnerA(Test test, long start) {
        this.test = test;
        this.start = start;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < numOfIncrement; i++) {
            test.incrementA();
        }
        System.out.println(System.currentTimeMillis() - start + " ms КОНЕЦ");
    }
}
