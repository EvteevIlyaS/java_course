import junit.framework.TestCase;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BankTest extends TestCase {
    public void testAccount() throws InterruptedException {
        long expected = 1500000;

        Bank.threadsRun();

        long actual = Bank.accounts.values().stream().map(el -> el.getMoney().longValue()).reduce(Long::sum).get();

        assertEquals(expected, actual);
    }

}
