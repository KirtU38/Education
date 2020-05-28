package com.company;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {

        final int PATIENTS = 30;
        final double MIN_GENERATED_TEMP = 32;
        final double MAX_GENERATED_TEMP = 40;
        final double MIN_HEALTHY_TEMP = 36.2;
        final double MAX_HEALTHY_TEMP = 36.9;
        float[] tempOfPatients = new float[PATIENTS];
        int healthyPatientsCount = 0;
        float avgTemp = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.0°C ");

        for (int i = 0; i < tempOfPatients.length; i++) { // Заполняем массив температурами
            float temp = (float)(MIN_GENERATED_TEMP + (Math.random() * (MAX_GENERATED_TEMP - MIN_GENERATED_TEMP))); // Приводим в нужынй формат
            tempOfPatients[i] = temp;

            avgTemp = avgTemp + temp / tempOfPatients.length; // Считаем среднюю

            if (temp >= MIN_HEALTHY_TEMP && temp <= MAX_HEALTHY_TEMP) { // Проверяем и считаем здоровых
                healthyPatientsCount++;
            }
        }

        for (int i = 0; i < tempOfPatients.length; i++) { // Печатаем температуры через printf
            System.out.printf("%.1f" + "°C" + " ", tempOfPatients[i]);
        }
        System.out.println();

        for (int i = 0; i < tempOfPatients.length; i++) { // Печатаем температуры через DecimalFormat
            String tempFormatted = decimalFormat.format(tempOfPatients[i]);
            System.out.print(tempFormatted);
        }
        System.out.println();

        System.out.printf("Здоровые пациенты = %2$d Средняя температура по больнице = %1$.1f°C", avgTemp, healthyPatientsCount);
    }
}
