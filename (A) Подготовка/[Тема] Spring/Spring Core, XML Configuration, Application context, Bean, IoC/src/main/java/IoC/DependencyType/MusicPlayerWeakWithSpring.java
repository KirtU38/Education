package IoC.DependencyType;
import IoC.Classes.ClassicalMusic;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MusicPlayerWeakWithSpring {

    private ClassicalMusic classicalMusic;
    private ClassPathXmlApplicationContext context;

    public void playMusic() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        classicalMusic = context.getBean("classicalMusic", ClassicalMusic.class);
        System.out.println("Now playing " + classicalMusic.getSong());
    }
}
// Это Класс со СЛАБОЙ засисимостью, но который использует ApplicationContext Спринга и Bean "classicalMusic"
// Класса ClassicalMusic.
// Однако проблема все равно не решена, если захотим изменить, нам заново придется переписывать этот класс, и получается
// Класс MusicPlayer сам внедряет в себя зависимости.