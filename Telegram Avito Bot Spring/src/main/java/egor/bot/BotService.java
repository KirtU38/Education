package egor.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class BotService {

    private final ExecutorService executorService;
    private final HashMap<String, String> mapOfCities;

    public BotService() {
        this.executorService = Executors.newCachedThreadPool();
        this.mapOfCities = fillListWithCities();
    }

    public void executeUpdate(Update update) {
        executorService.execute(new MessageManager(update));
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public HashMap<String, String> getMapOfCities() {
        return mapOfCities;
    }

    private static HashMap<String, String> fillListWithCities() {

        return new HashMap<String, String>() {{
            put("алушта", "alushta");
            put("феодосия", "feodosiya");
            put("ялта", "yalta");
            put("севастополь", "sevastopol");
            put("симферополь", "simferopol");
            put("абакан", "abakan");
            put("анапа", "anapa");
            put("ангарск", "angarsk");
            put("архангельск", "arhangelsk");
            put("астрахань", "astrahan");
            put("барнаул", "barnaul");
            put("белгород", "belgorod");
            put("благовещенск", "amurskaya_oblast_blagoveschensk");
            put("чебоксары", "cheboksary");
            put("челябинск", "chelyabinsk");
            put("череповец", "cherepovets");
            put("черняховск", "chernyahovsk");
            put("чита", "chita");
            put("екатеринбург", "ekaterinburg");
            put("геленджик", "gelendzhik");
            put("иркутск", "irkutsk");
            put("ижевск", "izhevsk");
            put("кабардинка", "kabardinka");
            put("калининград", "kaliningrad");
            put("казань", "kazan");
            put("кемерово", "kemerovo");
            put("хабаровск", "habarovsk");
            put("ханты-Мансийск", "hanty-mansiysk");
            put("кисловодск", "kislovodsk");
            put("комсомольск-на-Амуре", "komsomolsk-na-amure");
            put("кострома", "kostroma");
            put("краснодар", "krasnodar");
            put("красноярск", "krasnoyarsk");
            put("курган", "kurgan");
            put("курск", "kursk");
            put("липецк", "lipetsk");
            put("магадан", "magadan");
            put("магнитогорск", "magnitogorsk");
            put("махачкала", "mahachkala");
            put("минеральные Воды", "mineralnye_vody");
            put("москва", "moskva");
            put("мурманск", "murmansk");
            put("находка", "nahodka");
            put("нальчик", "nalchik");
            put("нижневартовск", "nizhnevartovsk");
            put("нижний Новгород", "nizhniy_novgorod");
            put("ноябрьск", "noyabrsk");
            put("норильск", "norilsk");
            //////////////////////////////////
            put("ростов-на-дону", "rostov-na-donu");
            put("ханты-мансийск", "hanty-mansiysk");
            put("майма", "mayma");
            put("сант-питербург", "sankt-peterburg");
            put("сант-петербург", "sankt-peterburg");
            put("санкт-питербург", "sankt-peterburg");
            put("санкт-петербург", "sankt-peterburg");
            put("питер", "sankt-peterburg");
            put("новосибирск", "novosibirsk");
            put("новосиб", "novosibirsk");
            put("сиб", "novosibirsk");
        }};
    }
}
