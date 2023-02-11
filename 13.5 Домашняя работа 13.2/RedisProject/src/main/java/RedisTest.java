import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    private static void log(String userId) {
        String log = String.format("— На главной странице показываем пользователя %s", userId);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();

        String firstUser;
        String paidUser;
        int countPassedUsers = 0;

        Scanner scanner = new Scanner(in);

//        redis.printFields();

        for (int i = 0; i < 20; i++) {
            redis.addUser(String.valueOf(i));
            Thread.sleep(1000);
        }

        while (true) {
            if (countPassedUsers == 20) {
                out.println("Конец цикла, продолжить? [y/n]\n");
                if (!scanner.nextLine().equals("y")) {
                    redis.shutdown();
                    return;
                }
                countPassedUsers = 0;
                redis.remAllUsers();
                for (int i = 0; i < 20; i++) {
                    redis.addUser(String.valueOf(i));
                    Thread.sleep(1000);
                }
            }

            if (Math.random() <= 0.1) {
                paidUser = String.valueOf(new Random().nextInt(0, 20));
                out.printf("> Пользователь %s оплатил платную услугу%n", paidUser);
                redis.moveToTheStart(paidUser);
            }

            firstUser = redis.getFirstUser();
            log(firstUser);
            redis.moveToTheEnd(firstUser);

            countPassedUsers++;
        }
    }
}