package Behavioral.Interpreter;

import java.util.ArrayList;
import java.util.List;

public class Lexing {

}

class Token{
    enum Type {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }
    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}
class Demo {

    static List<Token> lex(String input) {
        final ArrayList<Token> tokens = new ArrayList<>();

        for(int i = 0; i <input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    break;

                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    break;

                case '(':
                    tokens.add(new Token(Token.Type.LPAREN, "("));
                    break;

                case ')':
                    tokens.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j = i + 1; j <input.length(); j++) {
                        if(Character.isDigit(input.charAt(j))) {
                            sb.append(input.charAt(j));
                            i++;
                        } else {
                            tokens.add(new Token(Token.Type.INTEGER, sb.toString()));
                            break;
                        }
                    }
                    break;

            }
        }
        return tokens;
    }
    public static void main(String[] args) {
        final String input = "(13+3)-(12+2)";
        final List<Token> tokenList = lex(input);
        tokenList.forEach(System.out::println);
    }
}
