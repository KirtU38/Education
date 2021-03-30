package abstract_class;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public abstract class AbstractSortArrayClass {

    public abstract void sortArray(int[] array);

    @Test
    @DisplayName("Пустой массив")
    public void emptyArrayTest(){
        int[] expected = new int[0];
        int[] actual = new int[0];
        sortArray(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Не отсортированный случайный массив")
    public void unsortedArray(){
        Random random = new Random();
        int n = random.nextInt(100);
        int[] act = new int[n];
        int[] exp = new int[n];
        for (int i = 0; i < n; i++){
            int val = random.nextInt();
            act[i] = val;
            exp[i] = val;
        }
        sortArray(act);
        Arrays.sort(exp);
        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    @DisplayName("Массив из одинаковых значений")
    public void sortedEqualsValueArray(){
        int val = new Random().nextInt();
        int n = 10;
        int[] act = new int[n];
        int[] exp = new int[n];
        for (int i = 0; i < n; i++) {
            act[i] = val;
            exp[i] = val;
        }
        sortArray(act);
        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    @DisplayName("Отсортированный массив")
    public void sortedArray(){
        int n = 10;
        int[] act = new int[n];
        int[] exp = new int[n];
        for (int i = 0; i < n; i++) {
            act[i] = i;
            exp[i] = i;
        }
        sortArray(act);
        Assertions.assertArrayEquals(exp, act);
    }

}
