package com.company;

import com.company.Client.Entity;
import com.company.Client.Entrepreneur;
import com.company.Client.Individual;

public class Main {

    public static void main(String[] args) {

        Individual individual = new Individual();
        Entity entity = new Entity();
        Entrepreneur entrepreneur = new Entrepreneur();

        individual.putMoney(1000);
        individual.withdrawMoney(500);
        individual.getMoneyAmount();
        System.out.println();

        entity.putMoney(1000);
        entity.withdrawMoney(500);
        entity.getMoneyAmount();
        System.out.println();

        entrepreneur.putMoney(1000);
        entrepreneur.withdrawMoney(500);
        entrepreneur.getMoneyAmount();
        System.out.println();
    }
}
