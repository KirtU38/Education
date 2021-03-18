package ru.beloshitsky;

public class Basket {
  private String items;
  private int totalPrice;
  private int limit;
  private int totalWeight;

  public Basket() {
    this.totalPrice = 0;
    this.totalWeight = 0;
    items = "Список товаров:";
    this.limit = Integer.MAX_VALUE;
  }

  public Basket(int limit) {
    this();
    this.limit = limit;
  }

  public void add(String name, int price) {
    add(name, price, 1, 0);
  }

  public void add(String name, int price, int weight) {
    add(name, price, 1, weight);
  }

  public void add(String name, int price, int count, int weight) {
    if (items.contains(name) || totalPrice + (price * count) >= limit) {
      return;
    }
    items = items + "\n" + name + " - " + count + " шт. - " + price;
    totalPrice += price * count;
    totalWeight += weight * count;
  }

  public void clear() {
    items = "";
    totalPrice = 0;
  }

  public void print(String title) {
    System.out.println(title);
    if (items.isEmpty()) {
      System.out.println("Корзина пуста");
    } else {
      System.out.println(items);
    }
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public int getTotalWeight() {
    return totalWeight;
  }
}
