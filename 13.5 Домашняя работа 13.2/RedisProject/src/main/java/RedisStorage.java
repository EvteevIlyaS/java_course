import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.List;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> users;

    private final static String KEY = "USERS";

    private double getTs() {
        return new Date().getTime() / 1000;
    }

//    // Пример вывода всех ключей
//    public void listKeys() {
//        Iterable<String> keys = rKeys.getKeys();
//        for(String key: keys) {
//            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
//        }
//    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        try {
        redisson = Redisson.create(config);
//        } catch (RedisConnectionException Exc) {
//            out.println("Не удалось подключиться к Redis");
//            out.println(Exc.getMessage());
//        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    // Добавляет нового пользователя
    void addUser(String userId) {
        //ZADD USERS
        users.add(getTs(), userId);
    }

    // Двигает в конец очереди
    void moveToTheEnd(String userId) {
        // ZREM
        users.remove(userId);
        addUser(userId);
    }

    // Двигает в начало очереди
    void moveToTheStart(String userId) {
        // ZREM
        users.remove(userId);
        users.add(users.firstScore() - 1, userId);
    }

    // Получить первого пользователя
    String getFirstUser()
    {
        return users.first();
    }

    // Вывести все поля
    void printFields()
    {
        users.stream().forEach(el -> out.println(users.getScore(el)));
    }

    // Удалить всех пользователей
    void remAllUsers()
    {
        for (int i = 0; i < 20; i++) {
            users.remove("0");
        }
    }

}