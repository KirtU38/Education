package com.company.Regular;

public class RunnerB implements Runnable {

    Test test;

    public RunnerB(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            test.incrementB();
        }
    }
}
