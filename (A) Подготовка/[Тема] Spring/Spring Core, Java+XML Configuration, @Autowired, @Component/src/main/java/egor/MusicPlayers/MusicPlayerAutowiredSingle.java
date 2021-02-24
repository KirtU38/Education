package egor.MusicPlayers;
import egor.Classes.ClassicalMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayerAutowiredSingle {

    private ClassicalMusic classicalMusic;

    @Autowired
    public MusicPlayerAutowiredSingle(ClassicalMusic classicalMusic) {
        this.classicalMusic = classicalMusic;
    }

    public void playMusic() {
        System.out.println("Now playing " + classicalMusic.getSong());
    }
}
// Это вариант когда у Спринга есть только Один подходящий Бин для создания зависимости(ClassicalMusic), однако это
// только Тест, на самом деле MusicPlayer должен принимать все Жанры Music.
// Это нагладность того, когда только Один Бин подходит для внедрения зависимости.