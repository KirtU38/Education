package com;

public class Human implements Fighter{

    public void print(){
        System.out.println("Human");
    }

    @Override
    public void fight() {
        System.out.println("Fight");
    }
}
