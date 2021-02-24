package egor.MusicPlayers;
import egor.Classes.ClassicalMusic;
import egor.Classes.RockMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayerAutowiredTwoFields {

    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;

    @Autowired
    public MusicPlayerAutowiredTwoFields(ClassicalMusic classicalMusic, RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }


    public void playMusic() {
        System.out.println("Now playing " + classicalMusic.getSong());
        System.out.println("Now playing " + rockMusic.getSong());
    }
}
// Это как внедрять несколько зависимостей через @Autowired