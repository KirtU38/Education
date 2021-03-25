package binary_search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    @DisplayName("Пустой список")
    public void emptyListTest(){
        BinarySearch search = new BinarySearch(new ArrayList<>());
        assertEquals(-1 , search.search("string"));
    }

    @Test
    @DisplayName("Элемент в списке не найден")
    public void elementNotFound(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("string 1", "string 2"));
        BinarySearch search = new BinarySearch(list);
        assertEquals(-1, search.search("string"));
    }

    @Test
    @DisplayName("Элемент найден в отсортированном списке")
    public void elementInSortList(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("string 1", "string 2", "string 3", "string 4", "string 5"));
        BinarySearch search = new BinarySearch(list);
        assertEquals(3, search.search("string 4"));
    }

    @Test
    @DisplayName("Элемент найден в не отсортированном списке")
    public void elementInList(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("string 5", "string 3", "string 12", "string 2", "string 4"));
        BinarySearch search = new BinarySearch(list);
        assertEquals(3, search.search("string 4"));
    }

}