package com.company.Regular;

public class RunnerE implements Runnable {

    Test test;

    public RunnerE(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            test.incrementE();
        }
    }
}
