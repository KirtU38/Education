package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        int[] array = {8, 1, 4, 9, 2, 5, 0, 6, 7, 3};

        int mid = array.length / 2;
        int[] left = new int[mid];
        System.arraycopy(array, 0, left, array[mid], mid);
        System.out.println(Arrays.toString(left));

        //split(array);
    }

    public static void split(int[] array) {

        int mid = array.length / 2;
        int[] left = new int[mid];
        System.arraycopy(array, 0, left, array[mid], mid);
    }
}


