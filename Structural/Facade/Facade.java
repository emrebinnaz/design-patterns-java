package Structural.Facade;

import java.math.BigDecimal;

class Client {
    public String name;
    public String surname;
    public String id;

}
class Bank {
    public boolean canCreditAvailable(Client client, BigDecimal amount) {
        // add some logic
        return true;
    }
}

class BlackListCheck {

    public boolean isClientAtBlackList(String id) {
        // add some logic
        return false;
    }
}
public class Facade {

    private Bank bank;
    private Client client;
    private BlackListCheck blackListCheck;

    public Facade(Bank bank, Client client, BlackListCheck blackListCheck) {
        this.bank = bank;
        this.client = client;
        this.blackListCheck = blackListCheck;
    }

    void useCredit(Client client, BigDecimal amount) {
        if( !blackListCheck.isClientAtBlackList(client.id) && bank.canCreditAvailable(client, amount)) {
            System.out.println("Credit available for client");
        }
    }
}
