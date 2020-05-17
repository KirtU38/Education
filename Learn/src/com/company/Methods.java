package com.company;

public class Methods {

    public static int powerNumber(int number, int power){
        int result = 0;
        for (int i = 1; i < power; i++) {
            if (i==1)
            {
                result = number * number;
            } else {
                result = result * number;
            }
        }
        return result;
    }
}
