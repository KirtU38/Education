package rabin_karp;

import java.util.*;

public class RabinKarpExtended {
  private final String text;
  private final Map<Character, Integer> indexedLetters;

  public RabinKarpExtended(String text) throws Exception {
    this.text = text.toLowerCase(Locale.ROOT);
    indexedLetters = new TreeMap<>();
    createIndex();
  }

  public List<Integer> search(String query) {
    List<Integer> indices = new ArrayList<>();
    Map<Integer, Long> indexedText = new TreeMap<>();
    query = query.toLowerCase(Locale.ROOT);
    if(query.isEmpty()){
      return indices;
    }
    
    // Для каждой позиции в тексте узнали хэш ближайший символов по кол-ву букв искомой строки
    int windowSize = query.length();
    for (int i = 0; i <= text.length() - windowSize; i++) {
      StringBuilder windowHash = new StringBuilder();
      for (int j = 0; j < windowSize; j++) {
        Integer hashOfLetter = indexedLetters.get(text.charAt(i + j));
        windowHash.append(hashOfLetter);
      }
      indexedText.put(i, Long.parseLong(windowHash.toString()));
    }
    System.out.println(indexedText + " - хэши каждой позиции в тексте");

    // Находим хэш искомой строки
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < query.length(); i++) {
      Integer hashOfLetter = indexedLetters.get(query.charAt(i));
      if(hashOfLetter == null){
        return indices;
      }
      builder.append(hashOfLetter);
    }
    int hashOfPattern = Integer.parseInt(builder.toString());
    System.out.println(hashOfPattern + " - искомый хэш");

    // И собственно просто сверяем каждую позицию в Мапе индексированного текста с паттерном
    for (Integer i : indexedText.keySet()) {
      if (indexedText.get(i) == hashOfPattern) {
        for (int j = 0; j < windowSize; j++) {
          indices.add(i + j);
        }
      }
    }
    return indices;
  }

  private void createIndex() throws Exception {
    // Создали "словарь" из данного текста и каждой букве присвоили число
    int index = 1;
    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      if (!indexedLetters.containsKey(ch)) {
        indexedLetters.put(ch, index);
        index++;
      }
    }
    System.out.println(indexedLetters + " - хэши каждой буквы");
    if (indexedLetters.size() > 9) {
      throw new Exception("Should be only 9 different letters");
    }
  }
}
