package bubble_sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BubbleSortTest {

  @Test
  @DisplayName("Отсортировать null массив")
  public void nullArrayTest() {
    assertThrows(Exception.class, () -> BubbleSort.sort(null));
  }
  
  @Test
  @DisplayName("Отсортировать пустой массив")
  public void emptyArrayTest() {
    assertThrows(Exception.class, () -> BubbleSort.sort(null));
  }

  @Test
  @DisplayName("Отсортировать массив")
  public void sortTest() throws Exception {
    int[] actual = {0, 9, 2, 7, 1, 5, 4, 3, 8, 6};
    int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    BubbleSort.sort(actual);
    for (int i = 0; i < actual.length; i++) {
      assertEquals(actual[i], expected[i]);
    }
  }
}
