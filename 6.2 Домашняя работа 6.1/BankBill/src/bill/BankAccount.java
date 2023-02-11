package bill;

public class BankAccount {
    private double moneyOnAccount;

    public BankAccount(double money) {
        this.moneyOnAccount = money;
    }

    double getMoneyOnAccount() {
        return moneyOnAccount;
    }

    void setMoneyOnAccount(double moneyOnAccount) {
        this.moneyOnAccount = moneyOnAccount;
    }

    public void takeMoney(double moneyTaken) {
        moneyOnAccount -= moneyTaken;
    }

    public void putMoney(double moneyPut) {
        moneyOnAccount += moneyPut;
    }

    public void showBalance() {
        System.out.println("На счете осталось " + moneyOnAccount);
    }

    public boolean send(BankAccount receiver, double amount) {
        if (moneyOnAccount - amount >= 0) {
            takeMoney(amount);
            receiver.putMoney(amount);
            return true;
        }
        return false;
    }
}
