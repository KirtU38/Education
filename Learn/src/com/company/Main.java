package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Human> list = new ArrayList<>();
        list.add(new Human("Egor", 12));
        list.add(new Human("Jake", 54));
        list.add(new Human("Caroline", 26));
        list.add(new Human("Dory", 18));

        list
                .stream()
                .mapToInt(Human::getAge)
                .average();


    }
}

