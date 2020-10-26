package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String one = "asdadasd";

        List<People> people = new ArrayList<>();
        people.add(new People("Alex", 25));
        people.add(new People("Jason", 10));
        people.add(new People("Kate", 2));
        people.add(new People("Clark", 45));

        people.stream().
                filter(p -> p.getAge() > 4 && p.getAge() < 40).
                sorted((o1, o2) -> {
                    return o1.getName().compareTo(o2.getName());
                }).
                forEach(System.out::println);


    }
}

