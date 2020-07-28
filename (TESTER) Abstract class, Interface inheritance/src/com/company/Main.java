package com.company;

public class Main {

    public static void main(String[] args) {

        Alive alive = new Human();
        Sapiens sapiens = new Human();
        Human human = new Human();
        Walkable walker = new Human();

        alive.beAlive();
        alive.beSapiens();
        alive.beHuman();
        alive.walk();

        sapiens.beAlive();
        sapiens.beSapiens();
        sapiens.beHuman();
        sapiens.walk();

        human.beAlie();
        human.beSapiens();
        human.beHuman();
        human.walk();

        walker.beAlive();
        walker.beSapiens();
        walker.beHuman();
        walker.walk();

    }
}

