package Behavioral.Memento;

class Memento {
    private final int balance; // it should not change
    public Memento(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;

    }
    public Memento deposit(int amount) {
        //returning Memento class allows us to roll back.
        balance += amount;
        return new Memento(balance);
    }
    public void restore(Memento memento) {
        balance = memento.getBalance();
    }

    @Override
    public String toString() {
        return "bank account : with "  + balance;
    }
}
class Demo {
    public static void main(String[] args) {
        final BankAccount bankAccount = new BankAccount(100);
        final Memento m1 = bankAccount.deposit(50);
        final Memento m2 = bankAccount.deposit(25);
        System.out.println(bankAccount); // 175 balance

        //restore to m1
        bankAccount.restore(m1);
        System.out.println(bankAccount); // 150;

        bankAccount.restore(m2);
        System.out.println(bankAccount); // 175;

    }
}