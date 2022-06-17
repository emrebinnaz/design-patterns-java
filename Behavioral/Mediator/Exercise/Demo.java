package Behavioral.Mediator.Exercise;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    Mediator mediator;
    int value = 0;
    public Participant(Mediator mediator) {
        this.mediator = mediator;
        mediator.addListener(this);
    }

    public void say(int n){
        mediator.addValue(this, n);

    }

    public void setValue(int n, Participant sender) {
        if(sender != this) {
            value = n;
        }
    }
}

class Mediator
{
    List<Participant> participantList = new ArrayList<>();

    public void addValue(Participant p, int value) {
        for (Participant participant: participantList) {
            participant.setValue(value, p);
        }
    }

    public void addListener(Participant participant) {
        this.participantList.add(participant);
    }
}
public class Demo {
    public static void main(String[] args) {

        Mediator mediator = new Mediator();
        Participant p1 = new Participant(mediator);
        Participant p2 = new Participant(mediator);

        System.out.println(p1.value);
        System.out.println(p2.value);

        p1.say(2);

        System.out.println(p1.value);
        System.out.println(p2.value);

        p2.say(4);

        System.out.println(p1.value);
        System.out.println(p2.value);
    }
}
