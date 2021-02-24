package egor.MusicPlayers;
import egor.Classes.Music;

public class MusicPlayerNoComponent {

    private Music music;

    public MusicPlayerNoComponent(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Now playing " + music.getSong());
    }
}
// Это пока Спринг не создает сам Бин из MusicPlayer, то есть не ставим здесь @Component, первая строка в Main