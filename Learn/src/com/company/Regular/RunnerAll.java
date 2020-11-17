package com.company.Regular;

public class RunnerAll implements Runnable {

    Test test;

    public RunnerAll(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            test.incrementAll();
        }
    }
}
