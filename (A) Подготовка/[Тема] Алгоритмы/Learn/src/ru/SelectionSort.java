package ru;

public class SelectionSort {
  public static void main(String[] args) {
    int[] arr = {9, 4, 7, 5, 6, 8, 0, 1, 2, 3};

    for (int i = 0; i < arr.length; i++) {
      int min = arr[i];
      int indexOfMin = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < min) {
          min = arr[j];
          indexOfMin = j;
        }
      }
      if (min != arr[i]) {
        int swapped = arr[i];
        arr[i] = min;
        arr[indexOfMin] = swapped;
      }
    }
  }
}
