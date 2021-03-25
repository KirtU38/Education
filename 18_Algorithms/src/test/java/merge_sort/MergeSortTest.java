package merge_sort;

import abstract_class.AbstractSortArrayClass;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest extends AbstractSortArrayClass {

    @Override
    public void sortArray(int[] array) {
        MergeSort.mergeSort(array);
    }
}