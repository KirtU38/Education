package paymentMethods.paymentMethod;

public class CreditCard implements PaymentMethod {
  @Override
  public void pay(int amount) {
    System.out.println("Payed with Credit Card: " + amount);
  }
}
