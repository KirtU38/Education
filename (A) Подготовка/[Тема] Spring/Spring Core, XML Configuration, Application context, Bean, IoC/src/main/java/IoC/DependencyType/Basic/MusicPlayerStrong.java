package IoC.DependencyType.Basic;
import IoC.Classes.ClassicalMusic;

public class MusicPlayerStrong {

    private ClassicalMusic classicalMusic;

    public void playMusic() {
        classicalMusic = new ClassicalMusic();
        System.out.println("Now playing " + classicalMusic.getSong());
    }
}
// Это Класс с СИЛЬНОЙ засисимостью, потому что он принимает только один Жанр музыки и САМ создаёт Обьект этого жанра

// Минусы:
// Если мы захотим поменять сыграть RockMusic, нам нужно либо писать отдельный Класс, который будет играть RockMusic,
// либо переписывать у этого тип переменной ClassicalMusic на RockMusic и внутри метода тоже создавать соответствующий
// Обьект, то есть много рефакторинга, плохая архитектура.