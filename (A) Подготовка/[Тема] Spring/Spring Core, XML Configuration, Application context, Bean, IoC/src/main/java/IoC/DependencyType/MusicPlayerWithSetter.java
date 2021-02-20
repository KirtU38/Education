package IoC.DependencyType;

import IoC.Classes.Music;

public class MusicPlayerWithSetter {

    private String name;
    private Music music;
    // Даже если нет на него Сеттера, такие поля делать можно(но смысла нет)
    private int age;

    public void playMusic() {
        System.out.println("Now playing " + music.getSong());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
// Это класс для теста создания Бинов через Сеттеры, на каждую переменную которую хотим создать должен быть Сеттер
// также в Классе обязан быть Дефолтный конструктор(если вообще конструкторов нет, Джава создает Дефолтный сама)

// В XML нужно указывать не "setMusic", а просто "music" с первой маленькой буквы, но остальные ровно как в Сеттере.
// setMusic = "music"
// setMusicNow = "musicNow"

// Getters Геттеры нужно только для получения значений, сам Спринг может создавать и без них