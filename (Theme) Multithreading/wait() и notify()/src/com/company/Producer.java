package com.company;

public class Producer implements Runnable {

    final Parking parking;

    public Producer(Parking parking) {
        this.parking = parking;
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            parking.in();
        }
    }

    /*public synchronized void in() {

        synchronized (parking){
            if (parking.carsCount == parking.parkingSize) {
                try {
                    System.out.println("wait() - БОЛЬШЕ НЕТ МЕСТА   " + Thread.currentThread().getName());
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            parking.carsCount++;
            System.out.println("+++");
            System.out.printf("%d машин, осталось %d мест   %s%n", parking.carsCount, parking.parkingSize - parking.carsCount,
                    Thread.currentThread().getName());
            notify();
        }
    }*/
}
