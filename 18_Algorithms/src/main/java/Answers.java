import binary_search.BinarySearch;
import bubble_sort.BubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answers {
  public static void main(String[] args) throws Exception {
    int[] arr = {1,5,9,0,8,7,4,3,2,679};
    BubbleSort.sort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
// Задание 2:
// 1) O(1), тк он уже отсортирован, то минимальное значение будет вначале массива.
// 2) O(n), тк ему точно придется пройтись один раз по массиву.
// 3) O(1), метод length() возвращает значение моментально, если же реализовывать алгоритм вручную,
// то тогда O(n).
// 4) O(n2), n во второй степени.
