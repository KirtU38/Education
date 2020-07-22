package com.company;

public class Main {

    public static void main(String[] args) {

        CheckingAccount checkingAccount = new CheckingAccount(2000);
        DepositAccount depositAccount = new DepositAccount(2000);
        CardAccount cardAccount = new CardAccount(2000);

        System.out.println("Рассчетный счет:");
        checkingAccount.putMoney(100);
        checkingAccount.withdrawMoney(500);
        checkingAccount.getMoneyAmount();

        System.out.println("Депозитный счет:");
        depositAccount.putMoney(100);
        depositAccount.withdrawMoney(500);
        depositAccount.getMoneyAmount();

        System.out.println("Карточный счет:");
        cardAccount.putMoney(100);
        cardAccount.withdrawMoney(500);
        cardAccount.getMoneyAmount();
    }
}

