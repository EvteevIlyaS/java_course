package bill;

public class CardAccount extends BankAccount{

    public CardAccount(double money) {
        super(money);
    }

    public void takeMoney(double moneyTake) {
        setMoneyOnAccount(getMoneyOnAccount() - moneyTake * 1.01);
    }

    public boolean send(BankAccount receiver, double amount) {
        if (getMoneyOnAccount() - amount >= 0) {
            takeMoney(amount);
            receiver.putMoney(amount);
            return true;
        }
        return false;
    }

}
