package Behavioral.Mediator;


import java.util.ArrayList;
import java.util.List;

class Person {

    public String name;
    public ChatRoom room;
    private List<String> chatLogs = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLogs.add(s);
    }

    public void say(String message) {
        room.broadcast(name, message);
    }
    
    public void sendPrivateMessage(String who, String message){
        room.message(name, who, message);
    }


}
public class ChatRoom {

    private List<Person> people = new ArrayList<>();

    public void broadcast(String name, String message) {
        for (Person person : people) {
            if (!person.name.equals(name))
                person.receive(name, message);
        }
    }

    public void message(String name, String destination, String message) {
        people.stream()
                .filter(p -> p.name.equals(destination))
                .findFirst()
                .ifPresent(person -> person.receive(name, message));
    }

    public void joinRoom(Person p) {
        String joinMessage = p.name + " "  + "joins the room";
        broadcast(p.name, joinMessage);
        p.room = this;
        people.add(p);
    }

    public void leftRoom(Person p) {
        String leftMessage = p.name + " "  + "left the room";
        broadcast(p.name, leftMessage);
        this.people.remove(p);
    }

    //Chatroom will act as mediator
    //Every class must have reference to mediator class in order to communication between.

    public static void main(String[] args)
    {
        ChatRoom room = new ChatRoom();

        Person john = new Person("John");
        Person jane = new Person("Jane");

        room.joinRoom(john); // no message here
        room.joinRoom(jane);

        john.say("hi room");
        jane.say("oh, hey john");

        Person simon = new Person("Simon");
        room.joinRoom(simon);
        simon.say("hi everyone!");

        jane.sendPrivateMessage("Simon", "glad you could join us!");

        room.leftRoom(simon);
    }
}
