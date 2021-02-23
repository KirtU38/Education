package egor.MusicPlayers;

import egor.Classes.ClassicalMusic;
import egor.Classes.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {

    // @Qualifier("rapMusic") - можно и так
    private Music music;

    @Autowired
    public MusicPlayer(@Qualifier("rapMusic") ClassicalMusic music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Now playing " + music.getSong());
    }
}
// У нас 3 Бина одновременно подходят для внедрения по Интерфейсу Music: classicalMusic, rockMusic, rapMusic.
// Чтобы "выбрать" какой именно должен быть внедрен, используется @Qualifier("уточнение").