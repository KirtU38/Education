package com;

import com.Classes.MySqlDatabase;
import com.DependencyExample.UserDAO;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO(new MySqlDatabase());
        userDAO.addUser();
    }
}
// В ApplicationContext мы описываем Обьекты, которые необходимы для работы нашего приложения, то есть они будут
// созданы при запуске нашего приложения.
// Spring сам создает эти Обьекты и берет контроль над ними(их жизненный цикл и многое др).

// То есть, до этого мы создавали Обьекты вручную "new MySqlDatabase", а Spring сам создаёт эти Обьекты.
// Также Spring сам внедряет все нужные зависимости в Обьекты, то есть производит Dependency Injection.
// Нам нужно только описать эту связь.
// Получается у всех Обьектов, созданных в AppclicationContext, уже внедрены все нужные зависимости(есть ссылки на все
// другие нужные Обьекты)


