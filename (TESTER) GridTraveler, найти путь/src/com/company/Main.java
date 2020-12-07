package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int num = 30;
    static int count = 0;
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        /*long start = System.currentTimeMillis();
        gridTraveler(num, num);
        System.out.println(System.currentTimeMillis() - start + " ms"); // 1870 ms 16
        System.out.println(count);*/

        long start1 = System.currentTimeMillis();
        int i = gridTravelerShort(num, num);
        System.out.println(System.currentTimeMillis() - start1 + " ms"); // 25 ms 16 // 70 ms 30
        System.out.println(i);
    }

    public static void gridTraveler(int height, int width) {

        if (height == 1 && width == 1) {
            count++;
            return;
        }

        if (height == 0 || width == 0) {
            return;
        }

        if (height != 1) {
            gridTraveler(height - 1, width);
        }
        if (width != 1) {
            gridTraveler(height, width - 1);
        }
    }

    public static int gridTravelerShort(int height, int width) {

        if (height == 1 && width == 1) {
            return 1;
        }

        String arr1 = (height - 1) + "-" + (width);
        String arr2 = (width) + "-" + (height - 1);
        int downRoot;
        if (map.containsKey(arr1) || map.containsKey(arr2)) {
            downRoot = map.get(arr1);
        } else {
            if (height - 1 == 0) {
                downRoot = 0;
            } else {
                downRoot = gridTravelerShort(height - 1, width);
                if (downRoot > 0) {
                    map.put(arr1, downRoot);
                    map.put(arr2, downRoot);
                }
            }
        }

        String arr3 = (height) + "-" + (width - 1);
        String arr4 = (width - 1) + "-" + (height);
        int rightRoot;
        if (map.containsKey(arr3) || map.containsKey(arr4)) {
            rightRoot = map.get(arr3);
        } else {
            if (width - 1 == 0) {
                rightRoot = 0;
            } else {
                rightRoot = gridTravelerShort(height, width - 1);
                if (rightRoot > 0) {
                    map.put(arr3, rightRoot);
                    map.put(arr4, rightRoot);
                }
            }
        }

        return downRoot + rightRoot;
    }
}


