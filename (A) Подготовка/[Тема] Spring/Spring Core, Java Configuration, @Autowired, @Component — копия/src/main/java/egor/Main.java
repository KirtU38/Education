package egor;

import egor.Classes.Music;
import egor.MusicPlayers.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // Старый конфиг через XML
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Это финальный и ПРАВИЛЬНЫЙ вариант, где внедряется Обьект типа Music(типа Интерфейса)
        // А какой именно Жанр мы уточняем через @Qualifier
        // Этот Класс указан со Scope("singleton"), смотри внутри Класса
        // Также здесь задана @Value на String name через внушний .properties файл, смотри MusicPlayer Класс
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();

        // А это мы ВРУЧНУЮ создали Бин через SpringConfig и аннотации @Bean
        MusicPlayer musicPlayerBean = context.getBean("musicPlayerBean", MusicPlayer.class);
        musicPlayerBean.playMusic();


        context.close();
    }
}
