package com.company;

public class Main {

    public static void main(String[] args) {

        Human human = new Human("Plumber", "Sharik", Skin.black);
        System.out.println(human.getJob());
        System.out.println(human.animal.name);
        System.out.println(human.skinColor);

    }
}

