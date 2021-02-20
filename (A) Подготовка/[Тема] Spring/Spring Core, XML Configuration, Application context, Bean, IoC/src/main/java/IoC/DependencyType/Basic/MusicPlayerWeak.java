package IoC.DependencyType.Basic;
import IoC.Classes.ClassicalMusic;

public class MusicPlayerWeak {

    private ClassicalMusic classicalMusic;

    public void playMusic() {
        classicalMusic = new ClassicalMusic();
        System.out.println("Now playing " + classicalMusic.getSong());
    }
}
// Это Класс со СЛАБОЙ засисимостью, потому что он принимает ВСЕ Жанры музыки, но все равно САМ создаёт Обьект
// нужного жанра

// Минусы:
// Если мы захотим поменять ClassicalMusic на RockMusic, нам нужно будет остановить приложение и переписать этот Класс
// MusicPlayer, поменяв ClassicalMusic на RockMusic и заново Компилировать код.