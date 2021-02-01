package redis;

import java.util.List;
import java.util.Random;

public class RedisTest {

    private static final int NUMBER_OF_USERS = 20;
    private static final int SLEEP = 5;
    private static final Random RANDOM = new Random();
    private static final RedisStorage redis = new RedisStorage();

    public static void main(String[] args) throws InterruptedException {


        for (; ; ) {
            addUsers();

            String lastUserInIteration = redis.lastUser();
            List<String> usersBeforeChange = (List<String>) redis.getUsersSet().valueRange(0, 20);
            System.out.println(usersBeforeChange);

            System.out.println("ITERATION:");
            for (; ; ) {
                int randomUser = RANDOM.nextInt(20) + 1;

                int randomNumber = RANDOM.nextInt(100);
                if (randomNumber < 10) {
                    if(randomUser == Integer.parseInt(lastUserInIteration)){
                        lastUserInIteration = usersBeforeChange.get(1);
                    }
                    payForPromotion(randomUser, lastUserInIteration);
                }

                log(redis.displayedUser());
                redis.moveToTheEnd(redis.displayedUser(), redis.endOfTheList());
                Thread.sleep(1000);

                if (redis.displayedUser().equals(lastUserInIteration)) {
                    log(redis.displayedUser());
                    redis.moveToTheEnd(redis.displayedUser(), redis.endOfTheList());
                    Thread.sleep(1000);
                    break;
                }
            }
        }

    }

    private static void log(String user_id) {
        System.out.println("— На главной странице показываем пользователя " + user_id);
    }

    private static void payForPromotion(int randomUser, String lastUserInIteration) {
        redis.addUser(randomUser);
        System.out.printf("> Пользователь %s оплатил платную услугу\n", randomUser);
        log(redis.displayedUser());
        redis.moveToTheEnd(redis.displayedUser(), redis.endOfTheList());
    }

    private static void addUsers() throws InterruptedException {
        for (int id = NUMBER_OF_USERS; id >= 1; id--) {
            redis.addUser(id);
            Thread.sleep(SLEEP);
        }
    }
}
