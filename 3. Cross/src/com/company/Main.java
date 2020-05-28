package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int n = 7;

        String[][] cross = new String[n][n];

        for (int i = 0; i < cross.length; i++) {
            Arrays.fill(cross[i], " ");             // Заполняем пробелами

            cross[i][i] = "x";                          // Ставим в нужные места X
            cross[i][cross.length - i - 1] = "x";

            for (int j = 0; j < cross.length; j++) {    // Распечатываем
                System.out.print(cross[i][j]);
            }
            System.out.println();
        }
    }
}



