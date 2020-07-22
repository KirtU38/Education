package com.company;

public class CardAccount extends Account {

    private static final double COMMISSION_PERCENT = 0.01;
    private double moneyAmount;

    public CardAccount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void putMoney(int amount) {

        moneyAmount += amount;
        System.out.printf("На счет добавлено: %d рублей %n", amount);
    }

    public void withdrawMoney(int amount) {

        double commission = amount * COMMISSION_PERCENT;

        moneyAmount -= amount + commission;
        System.out.printf("Со счета снято: %d рублей, коммисия в %.0f%% составила %.1f рублей %n", amount,
                COMMISSION_PERCENT * 100, commission);
    }

    public void getMoneyAmount() {

        System.out.printf("На вашем счете осталось: %.1f рублей %n", moneyAmount);
    }
}
