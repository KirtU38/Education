package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4};
        int[] arr2 = arr.clone();

        arr[0] = 15;

        System.out.println(Arrays.toString(arr2));
    }
}


