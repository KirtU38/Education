package com.company;

public class Array {

    int lastIndex = 0;
    int[] arr;
    int[] arrCopy;
    int length;
    int size = 0;

    public Array(int length) {

        this.length = length;
        arr = new int[length];
    }

    public void insert(int item) {

        size++;

        if (size > arr.length) {
            arrCopy = new int[arr.length];
            System.arraycopy(arr, 0, arrCopy, 0, arr.length);
            arr = new int[size];
            System.arraycopy(arrCopy, 0, arr, 0, arrCopy.length);
            arr[arr.length - 1] = item;
        } else {
            arr[lastIndex] = item;
        }
        lastIndex++;
    }

    public void print() {

        for (int i = 0; i < lastIndex; i++) {
            System.out.println(arr[i]);
        }
    }

    public void removeAt(int index) {

        arrCopy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrCopy[i] = arr[i];
        }

        arr = new int[arrCopy.length - 1];
        for (int i = 0; i < index; i++) {
            arr[i] = arrCopy[i];
        }
        for (int i = index; i < arr.length; i++) {
            arr[i] = arrCopy[i + 1];
        }
        size--;
        lastIndex--;
    }
}
