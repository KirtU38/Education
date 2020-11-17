package com.company.Regular;

public class RunnerC implements Runnable {

    Test test;

    public RunnerC(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            test.incrementC();
        }
    }
}
