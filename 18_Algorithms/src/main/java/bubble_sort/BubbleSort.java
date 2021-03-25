package bubble_sort;

public class BubbleSort {
  public static void sort(int[] array) throws Exception {
    if (array == null || array.length == 0) {
      throw new Exception("Array is empty or null");
    }

    int n = array.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (array[j + 1] < array[j]) {
          swap(array, j, j + 1);
        }
      }
    }
  }

  private static void swap(int[] arr, int i1, int i2) {
    int swapped = arr[i1];
    arr[i1] = arr[i2];
    arr[i2] = swapped;
  }
}
