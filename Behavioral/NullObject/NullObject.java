package Behavioral.NullObject;

import java.lang.reflect.Proxy;

interface Log {
    void info(String detail);
    void warn();
}
interface Logx {
    String info(String detail);
    String warn();
}

class ConsoleLog implements Log  {

    @Override
    public void info(String detail) {
        System.out.println(detail);
    }

    @Override
    public void warn() {
        System.out.println("warning");
    }
}
class BankAccount {
    private  Log log;
    private int balance;

    public BankAccount(int balance, Log log) {
        this.balance = balance;
        this.log = log;

    }
    public void deposit(int amount) {
        balance += amount;
        log.info("Deposited " + amount); // hard dependency
        // Sometimes we dont want to log.
    }
}

 class NullLog implements Log {
    //no-op class
    private static NullLog INSTANCE;

    private NullLog() {

    }

    public static NullLog getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new NullLog();
        }
        return INSTANCE;
    }
    @Override
    public void info(String detail) {

    }

    @Override
    public void warn() {

    }
}
public class NullObject {

    public static void main(String[] args) {
        ConsoleLog consoleLog = new ConsoleLog();
        BankAccount bankAccount = new BankAccount(100, consoleLog); // general usage
        // If we dont want to print logs, we put "null" referance for log object.
        BankAccount account = new BankAccount(100, null);
        // Then we check whether log object is null or not at the deposit function. It is defensively coding.
        //In order to avoid this problem, we have to create NullLog object.

        Log nullLog = NullLog.getInstance();
        BankAccount account1 = new BankAccount(100, nullLog);

    }
}
