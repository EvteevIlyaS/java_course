package bill;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DepositAccount extends BankAccount {
    private Date lastPutDate;

    public DepositAccount(double money) {
        super(money);
        lastPutDate = new Date();
    }

    private Date getLastPutDate() {
        return lastPutDate;
    }

    public void takeMoney(double moneyTaken) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (Period.between(LocalDate.parse(sdf.format(getLastPutDate())),
                LocalDate.parse(sdf.format(new Date()))).getMonths() == 0) {
            System.out.println("Now you cant take money");
            return;
        }
        setMoneyOnAccount(getMoneyOnAccount() - moneyTaken);
    }

    public void putMoney(double moneyPut) {
        setMoneyOnAccount(getMoneyOnAccount() + moneyPut);
        lastPutDate = new Date();
    }

    public boolean send(BankAccount receiver, double amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (Period.between(LocalDate.parse(sdf.format(getLastPutDate())),
                LocalDate.parse(sdf.format(new Date()))).getMonths() == 0 &&
                getMoneyOnAccount() - amount >= 0) {
            setMoneyOnAccount(getMoneyOnAccount() - amount);
            receiver.setMoneyOnAccount(receiver.getMoneyOnAccount() + amount);
            return true;
        }
        return false;
    }
}
