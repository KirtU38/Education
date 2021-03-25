package binary_search;

import java.util.Collections;
import java.util.List;

public class BinarySearch {
  private List<String> list;

  public BinarySearch(List<String> list) {
    this.list = list;
  }

  public int search(String query) {
    if (list.isEmpty()) {
      return -1;
    }
    Collections.sort(list);
    return search(query, 0, list.size() - 1);
  }

  private int search(String query, int from, int to) {
    int mid = (from + to) / 2;
    String comparedString = list.get(mid);
    int comparison = query.compareTo(comparedString);
    
    if (from == to && comparison != 0) { // Условие отсутствия строки
      return -1;
    }
    if (comparison == 0) { // Найдена
      return mid;
    } else if (comparison > 0) { // Ищем справа
      return search(query, mid + 1, to);
    } else {
      return search(query, from, mid); // Ищем слева
    }
  }
}
