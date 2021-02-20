package com.DependencyExample;

import com.Classes.Database;

// Это Класс Data Acces Object, то есть Обьект этого Класса будет делать запросы к БД
public class UserDAO {

    // У него должна быть определена БД, к которой он будет подключаться
    // Это и есть Dependency или зависимость, то есть Класс UserDAO ЗАВИСИТ от Обьекта класса Database
    Database database;

    public UserDAO(Database database) {
        this.database = database;
    }

    public void addUser(){
        System.out.println("User added to " + database.getClass());
    }
}
