package array_max_value;

public class ArrayMaxValue {

  public static Integer getMaxValue(int[] array) throws Exception {
    if (array == null || array.length == 0) {
      throw new Exception("Array is empty or null");
    }
    int maxValue = Integer.MIN_VALUE;
    for (int value : array) {
      if (value > maxValue) {
        maxValue = value;
      }
    }
    return maxValue;
  }
}
// Поменял возвращаемое значение на Integer чтобы можно было вернуть null, тк возвращать 0 при null
// массиве или массиве с [0] элементов кажется некорректным.
