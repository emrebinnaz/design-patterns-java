package Behavioral.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {

    private int balance;
    private int overdraftLimit = -500;


    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited : " + amount + " , balance is now " + balance);
    }

    public void withdraw(int amount) {
        if(balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew : " + amount + " , balance is now " + balance);

        }

    }

    @Override
    public String toString() {
        return "Balance : " + balance;
    }
}

interface Command {
    void call();
    void undo();
}

class DepositCommand implements Command {
    // This is going to encapsulate the idea of performing some operation on a bank account

    private final BankAccount bankAccount;
    private final int amount;
    private boolean succeeded;

    public DepositCommand(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;

    }
    @Override
    public void call() {
        bankAccount.deposit(amount);
    }

    @Override
    public void undo() {
      bankAccount.withdraw(amount);
    }
}

class WithdrawCommand implements Command {

    private final BankAccount bankAccount;
    private final int amount;
    private boolean succeeded;

    public WithdrawCommand(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;

    }

    @Override
    public void call() {
        bankAccount.withdraw(amount);
    }

    @Override
    public void undo() {
        bankAccount.deposit(amount);
    }
}
class Demo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        System.out.println(bankAccount);
        final List<Command> bankAccountCommands = new ArrayList<>(List.of(
                new DepositCommand(bankAccount, 100),
                new WithdrawCommand(bankAccount,50)
        ));
        for (Command c : bankAccountCommands) {
            c.call();
            System.out.println(bankAccount);
        }

        Collections.reverse(bankAccountCommands);
        for (Command c : bankAccountCommands ) {
            c.undo();
            System.out.println(bankAccount);
        }
    }
}
