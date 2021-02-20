package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TypeSafety {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Программа просто заменяет все буквы в Стринге на иксы

        // Здесь нет type safety, в Листе может быть любой обьект
        // тк 0 индекс был стрингом, то всё сработало
        List<Object> list = List.of("Egor", "Tasia", 3);
        String s = (String)list.get(0);
        String result = s.replaceAll("\\w", "x");
        System.out.println(result);

        // Однако здесь 2й индекс это уже число, и мы не можем вызвать на нем метод replace()
        // из-за этого у нас вылезет Exception
        // Именно по этому Generics созданы чтобы обеспечить нам type safety
        String s1 = (String)list.get(2);
        String result1 = s.replaceAll("\\w", "x");
        System.out.println(result1);

        // А это реальный use case при переборе всех элементов массива
        // Мы просто хотим распечатать все элементы в UpperCase
        for (Object element : list) {
            String test = (String) element;
            System.out.println(test.toUpperCase());
        }
    }
}
// erasure - процесс стирания генериков в после компиляции, в рантайме JVM не знает про Generics
// По сути Generics просто обеспечивают нам type safety













