package com.company.Client;

public abstract class Client {

    protected double moneyAmount;

    public void putMoney(double amount) {

        moneyAmount += amount;
        System.out.printf("Вы положили на счет %.1f рублей %n", amount);
    }

    public void withdrawMoney(double amount) {

        moneyAmount -= amount;
        System.out.printf("Вы сняли со счета %.1f рублей %n", amount);
    }

    public void getMoneyAmount() {

        System.out.printf("У вас на счету %.1f рублей %n", moneyAmount);
    }
}
