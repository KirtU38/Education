package com.company;

public class Human extends Sapiens implements Walkable {

    String humanize = "I'm a human";

    public void beAlie() {
        System.out.println(alive);
    }

    public void walk() {
        System.out.println(walkAround);
    }

    public void beHuman(){
        System.out.println(humanize);
    }

}
