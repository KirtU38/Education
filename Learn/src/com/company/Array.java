package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Array {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("A000AA01");
        list.add("A000AA02");
        list.add("A000AA03");
        list.add("A000AA10");
        list.add("A000AA13");
        list.add("A000AA19");
        list.add("A000AA90");
        list.add("A000AA95");
        list.add("A000AA99");
        list.add("A000AA100");
        list.add("A000AA101");
        list.add("A000AA116");
        list.add("A000AA199");





        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
