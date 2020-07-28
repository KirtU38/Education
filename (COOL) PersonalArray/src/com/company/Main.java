package com.company;

public class Main {

    public static void main(String[] args) {

        Array numbers = new Array(3);
        numbers.insert(1);
        numbers.insert(2);
        numbers.insert(3);
        numbers.insert(4);
        numbers.removeAt(2);
        numbers.removeAt(1);
        numbers.insert(26);
        numbers.print();
    }
}

