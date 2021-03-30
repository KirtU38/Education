import java.util.LinkedHashMap;
import java.util.Map;

public class Answers {
  public static void main(String[] args) throws Exception {

    // Рабин Карп в одну стену текста
    String pattern = "qwe";
    // Каждой букве нужно задать число
    String text = "piyryqweutypiwtriqwe";
    Map<Character, Integer> map = new LinkedHashMap<>();

    // Создали "словарь" из данного текста и каждой букве присвоили число
    int index = 1;
    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      if (!map.containsKey(ch)) {
        map.put(ch, index);
        index++;
      }
    }
    if (map.size() > 9) {
      System.out.println("ERROR");
    }
    System.out.println(map);
    System.out.println(map.size());

    // Для каждой позиции в тексте узнали хэш ближайший 3х символов, 3 это длинна искомого паттера
    Map<Integer, Integer> indexedText = new LinkedHashMap<>();
    int windowSize = pattern.length();
    for (int i = 0; i <= text.length() - windowSize; i++) {
      StringBuilder windowHash = new StringBuilder();
      for (int j = 0; j < windowSize; j++) {
        windowHash.append(map.get(text.charAt(i + j)));
      }
      indexedText.put(i, Integer.parseInt(windowHash.toString()));
    }
    System.out.println(indexedText);

    // Находим хэш искомой строки
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < pattern.length(); i++) {
      builder.append(map.get(pattern.charAt(i)));
    }
    int hashOfPattern = Integer.parseInt(builder.toString());
    System.out.println(hashOfPattern);

    // И собственно просто сверяем каждую позицию в Мапе индексированного текста с паттерном
    for (Integer i : indexedText.keySet()) {
      if(indexedText.get(i) == hashOfPattern){
        System.out.println("Found on index " + i);
      }
    }
  }
}
// Задание 2:
// 1) O(1), тк он уже отсортирован, то минимальное значение будет вначале массива.
// 2) O(n), тк ему точно придется пройтись один раз по массиву.
// 3) O(1), метод length() возвращает значение моментально, если же реализовывать алгоритм вручную,
// то тогда O(n).
// 4) O(n2), n во второй степени.
// 5) Сделано
// 6) Сделано
// 7)
