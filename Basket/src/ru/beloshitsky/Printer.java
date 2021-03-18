package ru.beloshitsky;

public class Printer {
  private String queue;
  private int pagesCount;
  private int documentsCount;
  private int printedPagesCount;
  private int printedDocumentsCount;

  public Printer() {
    this.queue = "";
  }

  public void append(String text) {
    append(text, "", 1);
  }

  public void append(String text, String docName) {
    append(text, docName, 1);
  }

  public void append(String text, String docName, int pages) {
    queue +=
        "Текст документа: "
            + text
            + (docName.isEmpty() ? "" : ", Имя документа: " + docName)
            + ", Страницы: "
            + pages
            + "\n";
    pagesCount += pages;
    documentsCount++;
  }

  public void print() {
    if (queue.isEmpty()) {
      System.out.println("Очередь пуста");
      return;
    }
    System.out.println(queue);
    printedDocumentsCount += documentsCount;
    printedPagesCount += pagesCount;
    clear();
  }

  public void clear() {
    queue = "";
    pagesCount = 0;
    documentsCount = 0;
  }

  public int getPagesCount() {
    return pagesCount;
  }

  public int getDocumentsCount() {
    return documentsCount;
  }

  public int getPrinterStatictics() {
    return printedPagesCount + printedDocumentsCount;
  }
}
