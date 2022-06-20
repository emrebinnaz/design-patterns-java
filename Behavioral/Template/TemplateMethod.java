package Behavioral.Template;

abstract class Game {
    protected int currentPlayer;
    protected final int totalPlayerCount;

    protected Game(int totalPlayerCount) {
        this.totalPlayerCount = totalPlayerCount;

    }

    void run() {
        start();
        while( !haveWinner()) {
            takeTurn();
        }
        System.out.println("Player " + getWinningPlayer() + " wins");
        //skeleton of algorithm
    }

    protected abstract void start();
    protected abstract void takeTurn();
    protected abstract boolean haveWinner();
    protected abstract int getWinningPlayer();

}
class Chess extends Game {

    private int maxTurns = 10;
    private int turn = 1;

    protected Chess() {
        super(2);

    }

    @Override
    protected void start() {
        System.out.println("Game is starting");
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % this.totalPlayerCount;
    }

    @Override
    protected boolean haveWinner() {
        return turn == maxTurns;
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }
}
public class TemplateMethod {
    public static void main(String[] args) {
        new Chess().run();
    }
}
