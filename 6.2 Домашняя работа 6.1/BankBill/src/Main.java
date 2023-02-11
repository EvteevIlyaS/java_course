import bill.BankAccount;
import bill.CardAccount;
import bill.DepositAccount;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        BankAccount ba1 = new BankAccount(130000);
        BankAccount ba2 = new BankAccount(50000);

        DepositAccount da1 = new DepositAccount(40000);
        DepositAccount da2 = new DepositAccount(170000);

        CardAccount ca1 = new CardAccount(200000);
        CardAccount ca2 = new CardAccount(30000);

//        ba1.showBalance();
//        ba1.takeMoney(20000);
//        ba1.putMoney(30000);
//        ba1.showBalance();
//
//
//        da1.showBalance();
//        da1.takeMoney(20000);
//        da1.putMoney(30000);
//        da1.showBalance();
//
//        ca1.showBalance();
//        ca1.takeMoney(20000);
//        ca1.putMoney(30000);
//        ca1.showBalance();

//        ba1.showBalance();
//        ba2.showBalance();
//        ba1.send(ba2, 100000);
//        ba1.showBalance();
//        ba2.showBalance();
//
//        ca1.showBalance();
//        ba1.showBalance();
//        ca1.send(ba1, 100000);
//        ca1.showBalance();
//        ba1.showBalance();

    }
}
