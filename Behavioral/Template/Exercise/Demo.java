package Behavioral.Template.Exercise;
class Creature
{
    public int attack, health;

    public Creature(int attack, int health)
    {
        this.attack = attack;
        this.health = health;
    }
}

abstract class CardGame
{
    public Creature [] creatures;

    public CardGame(Creature[] creatures)
    {
        this.creatures = creatures;
    }

    // returns -1 if no clear winner (both alive or both dead)
    public final int combat(int creature1, int creature2)
    {
        // prevent override thanks to "final"
        Creature first = creatures[creature1];
        Creature second = creatures[creature2];
        hit(first, second);
        hit(second, first);
       if((first.health > 0 && second.health > 0)
               || (first.health <= 0 && second.health <= 0)) {
           return -1;
       } else if(first.health >= second.health && second.health <= 0) {
           return creature1;
        } else {
           return creature2;
       }
    }

    // attacker hits other creature
    protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame
{
    public TemporaryCardDamageGame(Creature[] creatures) {
        super(creatures);
    }

    @Override
    protected void hit(Creature attacker, Creature other) {

        if(attacker.attack >= other.health) {
            other.health -= attacker.attack;
        }
    }
}

class PermanentCardDamageGame extends CardGame
{
    public PermanentCardDamageGame(Creature[] creatures) {
        super(creatures);
    }

    @Override
    protected void hit(Creature attacker, Creature other) {

        other.health -= attacker.attack;
    }
}
public class Demo {
    public static void main(String[] args) {


        Creature c1 = new Creature(2, 2);
        Creature c2 = new Creature(2, 2);
        TemporaryCardDamageGame game = new TemporaryCardDamageGame(new Creature[]{c1, c2});
        System.out.println(game.combat(0, 1));
        /*

        Creature c1 = new Creature(1, 2);
        Creature c2 = new Creature(1, 3);
        CardGame game = new PermanentCardDamageGame(new Creature[]{c1, c2});
        System.out.println(game.combat(0, 1));
        System.out.println(game.combat(0, 1));

         */
    }
}
