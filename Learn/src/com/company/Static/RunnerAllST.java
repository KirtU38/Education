package com.company.Static;

public class RunnerAllST implements Runnable {

    @Override
    public void run() {
        System.out.println("STARTED   " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            TestStatic.incrementAll();
        }
    }
}
