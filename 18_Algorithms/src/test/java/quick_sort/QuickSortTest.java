package quick_sort;

import abstract_class.AbstractSortArrayClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest extends AbstractSortArrayClass {

    @Override
    public void sortArray(int[] array) {
        QuickSort.sort(array);
    }

}