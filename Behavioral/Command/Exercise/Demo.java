package Behavioral.Command.Exercise;
class Command
{
    enum Action
    {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public Command(Action action, int amount)
    {
        this.action = action;
        this.amount = amount;
    }
}

class Account
{
    public int balance;

    public void process(Command c) {
        if(c.action.equals(Command.Action.WITHDRAW)) {
            if(balance >= c.amount) {
                c.success = true;
                balance -= c.amount;
            }
        } else {
            balance += c.amount;
            c.success = true;
        }

    }
}
public class Demo {
    public static void main(String[] args) {
        Account a = new Account();

        Command command = new Command(Command.Action.DEPOSIT, 100);
        a.process(command);

        System.out.println(a.balance);
        System.out.println(command.success);

        command = new Command(Command.Action.WITHDRAW, 50);
        a.process(command);

        System.out.println(a.balance);
        System.out.println(command.success);

        command = new Command(Command.Action.WITHDRAW, 150);
        a.process(command);

        System.out.println(a.balance);
        System.out.println(command.success);

    }
}
