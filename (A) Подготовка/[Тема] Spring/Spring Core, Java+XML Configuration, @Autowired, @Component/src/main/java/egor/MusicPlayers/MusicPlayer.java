package egor.MusicPlayers;

import egor.Classes.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Scope("prototype") - этот Scope будет создавать новый MusicPlayer при каждом context.getBean()

@Component
@Scope("singleton")
public class MusicPlayer {

    // @Qualifier("rapMusic") - можно и так
    private final Music music;
    private final String name;

    @Autowired
    public MusicPlayer(@Qualifier("rapMusic") Music music,
                       @Value("${musicPlayer.name}") String name) {
        this.music = music;
        this.name = name;
        System.out.println("Constructor");
    }

    public void playMusic() {
        System.out.println("Now playing " + music.getSong() + " на плеере " + name);
    }

    // Так помечается "init-method", он запускается после Конструктора,
    // они доступны только через maven "javax.annotation-api", смотри pom.xml
    @PostConstruct
    public void initMethod(){
        System.out.println("MusicPlayer initialized");
    }

    // А это "destroy-method"
    @PreDestroy
    public void destroyMethod(){
        System.out.println("MusicPlayer destroyed");
    }
}
// У нас 3 Бина одновременно подходят для внедрения по Интерфейсу Music: classicalMusic, rockMusic, rapMusic.
// Чтобы "выбрать" какой именно должен быть внедрен, используется @Qualifier("уточнение").