package simpleExample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {

        // Это класс для создания Контекста из XML файла
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        // Сюда пишем id Бина, а потом Класс к которому принадлежит этот Бин
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean.getName());

        // context обязательно закрывать
        context.close();
    }
}
