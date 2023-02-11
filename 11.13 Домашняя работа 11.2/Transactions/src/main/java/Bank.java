import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    public static final HashMap<String, Account> accounts = new HashMap<>();
    private static final Set<String> blockedAccounts = new HashSet<>();

    public static void threadsRun() throws InterruptedException {
        accounts.put("123321", new Account(new AtomicLong(1000000), "123321"));
        accounts.put("112233", new Account(new AtomicLong(200000), "112233"));
        accounts.put("321123", new Account(new AtomicLong(300000), "321123"));

        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(() -> {
            try {
                transfer("123321", "321123", 400000);
                Thread.sleep(1000);
                transfer("321123", "112233", 200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        threads.add(new Thread(() -> {
            try {
                transfer("123321", "112233", 500000);
                Thread.sleep(1000);
                transfer("321123", "123321", 200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        threads.add(new Thread(() -> {
            try {
                transfer("112233", "123321", 100000);
                Thread.sleep(1000);
                transfer("321123", "123321", 300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

//        threads.forEach(Thread::start);

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++)
            es.execute(threads.get(i));
        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
//        System.out.println("Конец");
    }

    public synchronized static boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Fraud test");
        return Math.random() < 0.05;
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public static void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        if (amount > 300000) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                synchronized (blockedAccounts) {
                    blockedAccounts.add(fromAccountNum);
                    blockedAccounts.add(toAccountNum);
                }
                System.out.println("Fraud!");
                return;
            }
        }

        if (blockedAccounts.contains(fromAccountNum) | blockedAccounts.contains(toAccountNum)) {
            System.out.println("Blocked, change account number");
            return;
        }

        synchronized (accounts) {
            if (accounts.get(fromAccountNum).getMoney().longValue() < amount) {
                System.out.println("Insufficient funds in the account");
                return;
            }
            accounts.get(fromAccountNum).addMoney(-amount);
        }
        accounts.get(toAccountNum).addMoney(amount);

//        System.out.println(fromAccountNum + " : " + getBalance(fromAccountNum) +
//                "\n" + toAccountNum + " : " + getBalance(toAccountNum));
        System.out.println(amount);
        accounts.forEach((key, val) -> System.out.println(key + " - " + val.getMoney()));
        System.out.println();

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public static AtomicLong getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }
}
