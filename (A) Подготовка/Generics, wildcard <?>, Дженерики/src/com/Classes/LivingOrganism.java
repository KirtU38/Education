package com.Classes;

public class LivingOrganism {

    public void speak() {
        System.out.println("*Says something like it's alive*");
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
