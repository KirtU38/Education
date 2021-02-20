package IoC.DependencyType.Basic;
import IoC.Classes.Music;

public class MusicPlayerIoC {

    private Music music;

    public MusicPlayerIoC(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Now playing " + music.getSong());
    }
}
// Это Класс со СЛАБОЙ засисимостью и Обьект нужного типа(Classical или Rock) передаётся ему извне, это и есть реализация
// принципа Inversion Of Control или "Инверсия контроля"

// Минусы:
// Нам все равно в ручную нужно передавать Обьект в Конструктор этого Класса
// То есть Обьект например RockMusic нужно всё равно где-то создавать