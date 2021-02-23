package egor;

import egor.Classes.Music;
import egor.MusicPlayers.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // MusicPlayer ещё без @Component, создаем MusicPlayer вручную
        // Меняй названия Бина здесь и будет тебе счастье(rapMuisc, classicalMusic, rapMusic)
        Music music = context.getBean("rockMusic", Music.class);
        MusicPlayerNoComponent musicPlayerNuComponents = new MusicPlayerNoComponent(music);
        musicPlayerNuComponents.playMusic();

        // Здесь только один Бин подходит для создания Зависимости через @Autowired
        // (потому что в конструкторе не Music, а чисто ClassicalMusic)
        MusicPlayerAutowiredSingle musicPlayerAutowiredSingle = context.getBean("musicPlayerAutowiredSingle", MusicPlayerAutowiredSingle.class);
        musicPlayerAutowiredSingle.playMusic();

        // Здесь только один Бин подходит для создания Зависимости через @Autowired
        // И в Классе даже нет Конструктора, а только одна private переменная(поле) с @Autowired
        MusicPlayerAutowiredSinglePrivateFieldOnly musicPlayerAutowiredSinglePrivate = context.getBean(
                "musicPlayerAutowiredSinglePrivateFieldOnly", MusicPlayerAutowiredSinglePrivateFieldOnly.class);

        musicPlayerAutowiredSinglePrivate.playMusic();

        // Здесь мы внедрили сразу 2 зависимости в MusicPlayer - ClassicalMusic и RockMusic
        MusicPlayerAutowiredTwoFields musicPlayerAutowiredTwoFields = context.getBean(
                "musicPlayerAutowiredTwoFields", MusicPlayerAutowiredTwoFields.class);

        musicPlayerAutowiredTwoFields.playMusic();

        // Это финальный и ПРАВИЛЬНЫЙ вариант, где внедряется Обьект типа Music(типа Интерфейса)
        // А какой именно Жанр мы уточняем через @Qualifier
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();


        context.close();
    }
}
