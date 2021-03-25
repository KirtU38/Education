package merge_sort;

public class MergeSort {

  public static void sort(int[] array) {
    if (array.length <= 1) {
      return;
    }
    mergeSort(array);
  }

  private static void mergeSort(int[] array) {
    int n = array.length;
    if (n < 2) {
      return;
    }
    int mid = n / 2;
    int[] leftArray = new int[mid];
    System.arraycopy(array, 0, leftArray, 0, mid);
    int[] rightArray = new int[n - mid];
    System.arraycopy(array, mid, rightArray, 0, array.length - mid);

    mergeSort(leftArray);
    mergeSort(rightArray);

    merge(array, leftArray, rightArray);
  }

  private static void merge(int[] array, int[] leftArray, int[] rightArray) {
    int leftPointer = 0;
    int rightPointer = 0;

    for (int i = 0; leftPointer < leftArray.length && rightPointer < rightArray.length; i++) {
      if (leftArray[leftPointer] < rightArray[rightPointer]) {
        array[i] = leftArray[leftPointer];
        leftPointer++;
      } else {
        array[i] = rightArray[rightPointer];
        rightPointer++;
      }
    }
    // Перекидываем оставшиеся элементы с правой или с левой стороны в конец массива
    int commonIndex = leftPointer + rightPointer;
    if (leftPointer == leftArray.length) {
      for (int i = commonIndex; i < array.length; i++) {
        array[i] = rightArray[rightPointer];
        rightPointer++;
      }
    } else {
      for (int i = commonIndex; i < array.length; i++) {
        array[i] = leftArray[leftPointer];
        leftPointer++;
      }
    }
  }
}
