package com;

import java.util.ArrayList;

public class NoTypeParameter {

    public static void main(String[] args) {

        ArrayList hz = new ArrayList<>();
        hz.add("String");
        // hz.get(0).replaceAll("\\w", "x");
    }
}
// Если у Листа не задать Дженерик, то все Обьекты которые выходят из него теряют свой класс и становятся класса Object
// Именно поэтому мы не сможем вызвать метод Стринга replaceAll() на hz.get(0), потому что он выходит из листа
// как Object

