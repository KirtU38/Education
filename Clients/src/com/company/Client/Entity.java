package com.company.Client;

public class Entity extends Client {

    private static final double COMMISSION_PERCENT = 0.01;

    public void withdrawMoney(double amount) {

        double commission = amount * COMMISSION_PERCENT;
        moneyAmount -= amount + commission;
        System.out.printf("Вы сняли %.1f рублей, комиссия составил %.1f рублей %n", amount, commission);
    }
}
