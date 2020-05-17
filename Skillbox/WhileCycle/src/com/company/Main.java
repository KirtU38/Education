package com.company;

public class Main {

    public static void main(String[] args) {

        int i = 200000;
        int x = 220000;

       /* for (int i = 200000; i <= 210000; i++) {
            System.out.println("Номер билета: " + i);
        }
        for (int i = 220000; i <= 235000; i++) {
            System.out.println("Номер билета: " + i);
        }*/

        while(i <= 210000){
            System.out.println("Билет номер: " + i);
            i++;
        }
        while(x <= 235000){
            System.out.println("Билет номер: " + x);
            x++;
        }
    }
}
