import IoC.Classes.Music;
import IoC.Classes.RockMusic;
import IoC.DependencyType.*;
import IoC.DependencyType.Basic.MusicPlayerIoC;
import IoC.DependencyType.Basic.MusicPlayerStrong;
import IoC.DependencyType.Basic.MusicPlayerWeak;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // Тест Плеера с Сильной зависимостью, где он сам создает Обьект Classical Music внутри себя
        MusicPlayerStrong playerStrong = new MusicPlayerStrong();
        playerStrong.playMusic();

        // Тест Плеера со Слабой зависимостью, где он сам создает Обьект Classical Music внутри себя
        MusicPlayerWeak playerWeak = new MusicPlayerWeak();
        playerWeak.playMusic();

        // Тест Плеера со Слабой зависимостью, но где Обьект внутри создается Спрингом и используется Bean
        MusicPlayerWeakWithSpring player = new MusicPlayerWeakWithSpring();
        player.playMusic();

        // Тест Плеера с IoC, где мы зависимость передаем ему сами извне в Конструктор
        MusicPlayerIoC playerIoC = new MusicPlayerIoC(new RockMusic());
        playerIoC.playMusic();

        // Здесь мы уже не передаем вручную Обьекты класса в Конструктор каждого MusicPlayer`а, а создаем переменную
        // Родителя Music и она из ApplicationContext запрашивает нужный Обьект.
        // Теперь чтобы во всем приложении поменять музыку у всех MusicPlayer`ов нужно будет просто в
        // XML файле(ApplicationContext) поменять тип Бина "musicBean" на ClassicalMusic
        // Теперь не перекомпилирую и не идя в код, мы можем поменять Обьект, который будет создан.
        // То что мы здесь сделал, можно и нужно делать в самом Спринге, смотри XML конфигурационный файл "musicPlayerBean"
        // ОДНАКО МЫ ДО СИХ ПОР СОЗДАЕМ ОБЬЕКТ КЛАССА MusicPlayer ВРУЧНУЮ!
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Music musicBean = context.getBean("musicBean", Music.class);
        MusicPlayerIoC musicPlayer = new MusicPlayerIoC(musicBean);
        musicPlayer.playMusic();

        // А вот это уже намного короче и лучше, Спринг теперь сам знает как создавать Обьект MusicPlayer и сам кидает
        // внутрь в Конструктор нужный Обьект Класса Music
        // Всё это настроено в XML конфигурации
        MusicPlayerIoC musicPlayerBean = context.getBean("musicPlayerBean", MusicPlayerIoC.class);
        musicPlayerBean.playMusic();

        // Здесь мы создали Бин через Сеттеры и распечатали вдобавок name Плеера который тоже задали через Сеттер
        MusicPlayerWithSetter playerBeanWithSetter = context.getBean(
                "musicPlayerBeanWithSetter", MusicPlayerWithSetter.class);

        playerBeanWithSetter.playMusic();
        System.out.println(playerBeanWithSetter.getName());

        // Создадим класс MusicPlayerWithList, который будет внутри уже со всеми Жанрами которые хранятся в Листе,
        // и методом playAllSongs() сыграет все Жанры.
        MusicPlayerWithList playerWithList = context.getBean("musicPlayerBeanWithList", MusicPlayerWithList.class);
        playerWithList.playAllSongs();


    }
}
