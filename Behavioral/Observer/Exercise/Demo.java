package Behavioral.Observer.Exercise;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Game
{
   List<Rat> rats = new ArrayList<>();

   void addRat(Rat r) {
       this.rats.add(r);
       if(rats.size() > 1 ){
           attackWithSwarm();
       }
   }
   void dieRat(Rat r) {
       this.rats.remove(r);
       attackWithSwarm();
   }

   void attackWithSwarm() {
       rats.forEach(rat -> rat.setAttack(rats.size()));
   }
}

class Rat implements Closeable
{
    private Game game;
    public int attack = 1;

    public Rat(Game game)
    {
        this.game = game;
        game.addRat(this);
    }

    @Override
    public void close() throws IOException
    {
        game.dieRat(this);
    }

    public void setAttack(int newAttackValue) {
        this.attack = newAttackValue;
    }
}

public class Demo {
    public static void main(String[] args) {
        /*
        Game game = new Game();
        Rat rat = new Rat(game);
        System.out.println(rat.attack);
        */
        Game game = new Game();
        Rat rat = new Rat(game);
        Rat rat2 = new Rat(game);
        System.out.println(rat.attack);
        System.out.println(rat2.attack);

    }
}
