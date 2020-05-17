package com.company;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int numberOfBoxes = scanner.nextInt();
    static int containersInOneTruck = 12;
    static int boxesInOneContainer = 27;
    static int trucksCount = 0;
    static int containersCount = 0;
    static int boxesCount = 0;

    public static void main(String[] args) {

        addTruck();
        for (int j = 0; j < containersInOneTruck; j++) {
            addContainer();
            for (int k = 0; k < boxesInOneContainer; k++) {
                addBox();
            }
        }
    }


    public static void addTruck() {
        if (numberOfBoxes > boxesCount) {
            trucksCount++;
            System.out.println("Грузовик " + trucksCount + ":");
        }
    }

    public static void addContainer() {
        if (numberOfBoxes > boxesCount) {
            containersCount++;
            System.out.println("\tКонтейнер " + containersCount + ":");
        }
    }

    public static void addBox() {
        if (numberOfBoxes > boxesCount) {
            boxesCount++;
            System.out.println("\t  Ящик " + boxesCount + ":");
        }
    }
}

