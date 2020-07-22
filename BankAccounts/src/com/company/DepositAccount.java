package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DepositAccount extends Account {

    private static final int IRREVOCABLE_PERIOD_IN_DAYS = 30;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar irrevocablePeriodEnd = Calendar.getInstance();
    private double moneyAmount;

    public DepositAccount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void putMoney(int amount) {

        irrevocablePeriodEnd = Calendar.getInstance();

        int today = irrevocablePeriodEnd.get(Calendar.DATE);

        irrevocablePeriodEnd.set(Calendar.DATE, today + IRREVOCABLE_PERIOD_IN_DAYS);

        moneyAmount += amount;
        System.out.printf("На счет добавлено: %d рублей %n", amount);
    }

    public void withdrawMoney(int amount) {

        Calendar currentDate = Calendar.getInstance();

        if (currentDate.before(irrevocablePeriodEnd)) {
            String irrevocablePeriodEndSimple = dateFormat.format(irrevocablePeriodEnd.getTime());
            System.out.printf("Вы сможете снять деньги со счета %s %n", irrevocablePeriodEndSimple);
        } else {
            moneyAmount -= amount;
            System.out.printf("Со счета снято: %d рублей %n", amount);
        }
    }

    public void getMoneyAmount() {

        System.out.printf("На вашем счете осталось: %.1f рублей %n", moneyAmount);
    }
}

