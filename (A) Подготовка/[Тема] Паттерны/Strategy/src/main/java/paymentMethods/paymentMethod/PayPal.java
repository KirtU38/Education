package paymentMethods.paymentMethod;

public class PayPal implements PaymentMethod {
  @Override
  public void pay(int amount) {
    System.out.println("Payed with PayPal: " + amount);
  }
}
