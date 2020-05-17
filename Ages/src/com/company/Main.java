package com.company;

public class Main {

    static int dashaAge = 21;
    static int mishaAge = 6;
    static int ivanAge = 40;

    static int min = 0;
    static int middle = 0;
    static int max = 0;

    public static void main(String[] args) {

        checkAge(dashaAge);
        checkAge(mishaAge);
        checkAge(ivanAge);

        System.out.println("Minimal age: " + min + "\n" + "Middle age: " + middle + "\n" +
                "Maximum age: " + max);
    }

    public static void checkAge(int age){
        if(age>=dashaAge && age>=mishaAge && age>=ivanAge){
            max = age;
        } else if (age<=dashaAge && age<=mishaAge && age<=ivanAge){
            min = age;
        } else {
            middle = age;
        }
    }
}
