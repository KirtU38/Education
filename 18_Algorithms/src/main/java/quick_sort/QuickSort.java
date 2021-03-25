package quick_sort;

import javax.swing.*;
import java.util.Random;

public class QuickSort {

  int[] test = {8, 2, 5, 6, 9, 1, 9, 5};

  private static final Random random = new Random();

  public static void sort(int[] array) {
    if (array.length <= 1) {
      return;
    }
    sort(array, 0, array.length - 1);
  }

  private static void sort(int[] array, int from, int to) {
    if (from < to) {
      int pivot = partition(array, from, to);
      sort(array, from, pivot - 1);
      sort(array, pivot + 1, to);
    }
  }
  
  private static int partition(int[] arr, int from, int to) {
    int pivotIndex = to;
    int pivot = arr[pivotIndex];
    int leftPoiner = from;
    int rightPoiner = pivotIndex - 1;

    while (leftPoiner != rightPoiner) {
      while (arr[leftPoiner] <= pivot && leftPoiner != rightPoiner) {
        leftPoiner++;
      }
      while (arr[rightPoiner] >= pivot && leftPoiner != rightPoiner) {
        rightPoiner--;
      }
      if (arr[leftPoiner] > pivot && arr[rightPoiner] < pivot) {
        swap(arr, leftPoiner, rightPoiner);
      }
    }
    
    if (leftPoiner == rightPoiner && pivot < arr[rightPoiner]) {
      swap(arr, leftPoiner, pivotIndex);
      pivotIndex = rightPoiner;
    }
    return pivotIndex;
  }

  private static void swap(int[] arr, int i1, int i2) {
    int swapped = arr[i1];
    arr[i1] = arr[i2];
    arr[i2] = swapped;
  }
}
