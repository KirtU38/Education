package egor;

import egor.Classes.Music;
import egor.Classes.RapMusic;
import egor.MusicPlayers.MusicPlayer;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("egor")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

    // Этот Бин равен:
    // <bean id="musicBean"
    //       class="egor.Classes.RapMusic">
    // </bean>

    @Bean
    public RapMusic musicBean() {
        return new RapMusic();
    }

    // Этот Бин равен:
    // <bean id="musicPlayerBean"
    //       class="egor.MusicPlayers.MusicPlayer">
    //   <constructor-arg ref="musicBean"/>
    // </bean>

    // @Bean(value = "asd") - значит из Context мы будет getBean() именно по названию asd
    @Bean
    public MusicPlayer musicPlayerBean() {
        return new MusicPlayer(musicBean(), "Phillips");
    }
}
