package com.company;

public class Consumer implements Runnable {

    final Parking parking;

    public Consumer(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            parking.out();
        }
    }

    /*public synchronized void out() {

        synchronized (parking){
            if (parking.carsCount <= 0) {
                try {
                    System.out.println("wait() - БОЛЬШЕ НЕТ МАШИН   " + Thread.currentThread().getName());
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            parking.carsCount--;
            System.out.println("---   " + Thread.currentThread().getName());
            System.out.printf("%d машин, осталось %d мест   %s%n", parking.carsCount, parking.parkingSize - parking.carsCount,
                    Thread.currentThread().getName());
            notify();
        }
    }*/
}
