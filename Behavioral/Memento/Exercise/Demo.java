package Behavioral.Memento.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token
{
    public int value = 0;

    public Token(int value)
    {
        this.value = value;
    }
}

class Memento
{
    public List<Token> tokens = new ArrayList<>();
    private final int value;

    public Memento(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class TokenMachine
{
    public List<Token> tokens = new ArrayList<>();
    private int value;

    public Memento addToken(int value)
    {
        tokens.add(new Token(value));
        this.value = value;

        return new Memento(value);
    }

    public Memento addToken(Token token) {
        tokens.add(token);
        this.value = token.value;

        return new Memento(token.value);
    }

    public void revert(Memento m) {
        final int size = this.tokens.size();
        this.tokens.remove(size - 1);

        this.value = this.tokens.get(size - 2).value;
    }

    public int getValue() {
        return value;
    }

}

public class Demo {
    public static void main(String[] args) {

        TokenMachine tm = new TokenMachine();
        System.out.println("Made a token with value 111 and kept a reference");
        Token token = new Token(111);
        System.out.println("Added this token to the list");
        tm.addToken(token);
        Memento m = tm.addToken(222);
        System.out.println("Changed this token's value to 333 :)");
        token.value = 333;
        tm.revert(m);

        System.out.println(tm.tokens.size());
        System.out.println(tm.tokens.get(0).value);
    }
}
