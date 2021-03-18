package ru.beloshitsky;

public class Main {

  public static void main(String[] args) {
    Basket basket = new Basket();
    basket.add("Сок", 10, 5, 100);
    basket.add("Молоко", 100, 100);
    basket.print("Корзина:");
    System.out.println(basket.getTotalPrice() + " price");
    System.out.println(basket.getTotalWeight() + " weight\n");

    System.out.println("Arithmetics");
    Arithmetic arithmetic = new Arithmetic(10, 3);
    System.out.println(arithmetic.sum());
    System.out.println(arithmetic.difference());
    System.out.println(arithmetic.product());
    System.out.println(arithmetic.avg());
    System.out.println(arithmetic.max());
    System.out.println(arithmetic.min() + "\n");

    System.out.println("Printer");
    Printer printer = new Printer();
    printer.append("Hello world");
    printer.append("Bye world", "The art of saying goodbye");
    printer.append("Program to intefraces, not implementations", "Head-First Design Patterns", 7);
    System.out.println(printer.getPagesCount() + " pages count");
    System.out.println(printer.getDocumentsCount() + " documents count");
    printer.print();
    System.out.println(printer.getPagesCount() + " pages count after printing");
    System.out.println(printer.getDocumentsCount() + " documents count after printing");
    System.out.println(printer.getPrinterStatictics() + " printed items");
    printer.print();
  }
}
