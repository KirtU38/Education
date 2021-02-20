package com.Classes;

public class Human extends LivingOrganism {

    @Override
    public void speak() {
        super.speak();
    }

    public void doScience(){
        System.out.println("I'm doing science");
    }
}
// У нас есть Класс Human, его родитель LivingOrganism
// Human наследует от LivingOrganism класс speak() и добавляет свой doScience()
