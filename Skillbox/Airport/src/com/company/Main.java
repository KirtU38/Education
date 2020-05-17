package com.company;
import com.skillbox.airport.Airport;

public class Main {

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

        // Список самолетов
        System.out.println(airport.getAllAircrafts());

        // Количество самолетов
        System.out.println(airport.getAllAircrafts().size());
    }
}
