package com.company;

public class CheckingAccount extends Account{

    private double moneyAmount;

    public CheckingAccount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void putMoney(int amount) {

        moneyAmount += amount;
        System.out.printf("На счет добавлено: %d рублей %n", amount);
    }

    public void withdrawMoney(int amount) {

        moneyAmount -= amount;
        System.out.printf("Со счета снято: %d рублей %n", amount);
    }


    public void getMoneyAmount() {

        System.out.printf("На вашем счете осталось: %.1f рублей %n", moneyAmount);
    }
}
