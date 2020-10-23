package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(15);
        list.add(10);
        list.add(189);
        list.add(5);
        list.add(56);
        list.stream().filter(e->{
            return e >= 10 && e <= 100;
        }).forEach(System.out::println);
        System.out.println(list.get(0));

        Set<Integer> set = new HashSet<Integer>();
        set.add(19);
        set.add(90);
        set.add(46);
        set.add(2);





    }
}


