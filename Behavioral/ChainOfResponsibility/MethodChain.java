package Behavioral.ChainOfResponsibility;

import java.sql.SQLOutput;

class Creature {
    public String name;
    public int attack, defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature -> " + "name = " + name + " attack = " + attack + " defense =" + defense;
    }
}

class CreatureModifier {
    Creature creature;
    private CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }
    public void add(CreatureModifier creatureModifier) {
        if( next != null ){
            next.add(creatureModifier); // recursive adding for chain
        } else {
            next = creatureModifier;
        }
    }

    public void handle() {
        if(next != null) {
            next.handle();
        }
    }
}

class DoubleAttackModifier extends CreatureModifier {


    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Double attacking");
        creature.attack *= 2;
        super.handle();
    }
}

class DoubleDefenseModifier extends CreatureModifier {


    public DoubleDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Double defensing");
        creature.defense *= 2;
        super.handle();
    }
}
class NoBonusesModifier extends CreatureModifier {
    // It can break chain
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No bonuses");

    }
}
public class MethodChain {
    //one method calls entire methods
    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 2, 2);
        System.out.println(goblin);

        CreatureModifier root = new CreatureModifier(goblin);
        root.add(new DoubleAttackModifier(goblin));
        root.add(new DoubleDefenseModifier(goblin));
        root.handle();
        System.out.println(goblin);

    }

}
