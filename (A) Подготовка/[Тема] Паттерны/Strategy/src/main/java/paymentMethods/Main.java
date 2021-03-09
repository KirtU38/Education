package paymentMethods;

import paymentMethods.context.ShoppingCart;
import paymentMethods.paymentMethod.Cash;
import paymentMethods.paymentMethod.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    List<String> items =
        new ArrayList<>() {
          {
            add("Iphone 7");
            add("Macbook");
            add("AppleWatch");
          }
        };

    ShoppingCart shoppingCart = new ShoppingCart(items);

    shoppingCart.performPayment(new Cash(), 500);
  }
}
// У нас есть ShoppingCart которая принимает в себя Лист покупок, у неё есть метод performPayment(),
// который принимает аргументов любой PaymentMethod, и внутри происходит paymentMethod.pay(amount).
