package com.company;

public class Parking {

    int carsCount;
    int parkingSize = 500;

    public synchronized void in() {
        if (carsCount == parkingSize) {
            try {
                System.out.println("wait() - БОЛЬШЕ НЕТ МЕСТА   " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        carsCount++;
        System.out.println("+++   " + Thread.currentThread().getName());
        System.out.printf("%d машин, осталось %d мест   %s%n", carsCount, parkingSize - carsCount,
                Thread.currentThread().getName());
        notify();
    }

    public synchronized void out() {
        if (carsCount <= 0) {
            try {
                System.out.println("wait() - БОЛЬШЕ НЕТ МАШИН   " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carsCount--;
        System.out.println("---   " + Thread.currentThread().getName());
        System.out.printf("%d машин, осталось %d мест   %s%n", carsCount, parkingSize - carsCount,
                Thread.currentThread().getName());
        notify();

    }
}
