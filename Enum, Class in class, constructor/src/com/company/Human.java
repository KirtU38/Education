package com.company;

public class Human {

    private String job;
    Animal animal;
    Skin skinColor;

    public Human(String job, String animalName, Skin skinColor) {
        this.job = job;
        this.skinColor = skinColor;
        animal = new Animal(animalName);
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
