package paymentMethods.paymentMethod;

public class Cash implements PaymentMethod {
  @Override
  public void pay(int amount) {
    System.out.println("Payed with Cash: " + amount);
  }
}
