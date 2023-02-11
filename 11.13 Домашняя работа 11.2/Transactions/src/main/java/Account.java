import java.util.concurrent.atomic.AtomicLong;

public class Account
{
    private AtomicLong money;
    private String accNumber;

    public Account(AtomicLong money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public AtomicLong getMoney() {
        return money;
    }

    public void addMoney(long money) {
        this.money.addAndGet(money);
    }
}
