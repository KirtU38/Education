package redis;

import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import java.util.Date;

public class RedisStorage {

    private RedissonClient redisson;
    private final RScoredSortedSet<String> usersSet;

    private final static String KEY = "USERS";

    public RedisStorage() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }

        usersSet = redisson.getScoredSortedSet(KEY);
        redisson.getKeys().delete(KEY);
    }

    private long getTime() {
        return new Date().getTime();
    }

    void addUser(int user_id) {
        usersSet.add(getTime(), String.valueOf(user_id));
    }

    public void moveToTheEnd(String user, double score) {
        usersSet.add(score, user);
    }

    public double endOfTheList() {
        return usersSet.firstScore() - 1;
    }

    public String displayedUser(){
        return usersSet.last();
    }

    public String lastUser(){
        return usersSet.first();
    }

    public RScoredSortedSet<String> getUsersSet() {
        return usersSet;
    }
}
