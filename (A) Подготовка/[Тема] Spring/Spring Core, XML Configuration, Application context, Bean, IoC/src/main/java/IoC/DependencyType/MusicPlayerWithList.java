package IoC.DependencyType;

import IoC.Classes.Music;
import java.util.List;

public class MusicPlayerWithList {

    private List<Music> listOfMusic;

    public MusicPlayerWithList(List<Music> listOfMusic) {
        this.listOfMusic = listOfMusic;
    }

    public void playAllSongs(){
        listOfMusic.forEach(e-> System.out.println(e.getSong()));
    }
}
// Это Класс с Листом всех Жанров музыки, в который мы будем закидывать Бины в XML файле, соответственно здесь
// у нас есть зависимость от Листа, поэтому его тоже нужно закидывать в XML файл и создавать как Бин

// Чтобы создать Лист или Мапу в конфигурационном файле .xml нужно указать во первых:
// "xmlns:util="http://www.springframework.org/schema/util"

// А во вторых в "xsi:schemaLocation=" добавить
// "http://www.springframework.org/schema/util" и еще добавить
// "http://www.springframework.org/schema/util/spring-util.xsd"