package com.company.Regular;

public class RunnerD implements Runnable {

    Test test;

    public RunnerD(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            test.incrementD();
        }
    }
}