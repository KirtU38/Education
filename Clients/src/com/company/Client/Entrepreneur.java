package com.company.Client;

public class Entrepreneur extends Client{

    private static final double COMMISSION_PERCENT = 0.01;
    private static final double LESSER_COMMISSION_PERCENT = 0.005;

    public void putMoney(double amount) {

        double commission = 0;

        if(amount < 1000){
            commission = amount * COMMISSION_PERCENT;
            moneyAmount += amount - commission;
        }

        if(amount >= 1000){
            commission = amount * LESSER_COMMISSION_PERCENT;
            moneyAmount += amount - commission;
        }

        System.out.printf("Вы положили %.1f рублей, комиссия составила %.1f рублей %n", amount, commission);
    }
}
