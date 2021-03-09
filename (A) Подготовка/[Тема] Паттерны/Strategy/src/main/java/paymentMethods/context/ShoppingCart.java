package paymentMethods.context;

import paymentMethods.paymentMethod.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private List<String> items = new ArrayList<>();

  public ShoppingCart(List<String> items) {
    this.items = items;
  }

  public void performPayment(PaymentMethod paymentMethod, int amount) {
    paymentMethod.pay(amount);
  }
}
