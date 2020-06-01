package com.company;

public class Main {

    public static void main(String[] args) {

        String number = "aasd 7 999475 11-38 ash776";

        String result = number.replaceAll(".*(\\d)(\\s*|-*)(\\d{3})(\\s*|-*)(\\d{3})(\\s*|-*)(\\d{2})(\\s*|-*)(\\d{2}).*",
                "+$1-$3-$5-$7-$9");

        System.out.println(result);


    }
}

