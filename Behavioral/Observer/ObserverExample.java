package Behavioral.Observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
    //container of subscriptions
    private int count = 0;
    private Map<Integer, Consumer<TArgs>> subscribers = new HashMap<>();

    public Subscription addSubscriber(Consumer<TArgs> subscriber) {

        int i = count;
        subscribers.put(count++, subscriber);
        return new Subscription(this, i); // memento
    }

    void fire(TArgs args) {
        for(Consumer<TArgs> subscriber : subscribers.values()) {
            subscriber.accept(args);
        }
    }

    class Subscription<TArgs> implements AutoCloseable {

        private Event<TArgs> event;
        private int id; // map index

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }
        @Override
        public void close() throws Exception {
            event.subscribers.remove(id);
            // unsubscribe
        }
    }
}



class PropertyChangedEventArgs {
    public Object source;
    public String propertyName;

    public PropertyChangedEventArgs(Object source, String propertyName)
    {
        this.source = source;
        this.propertyName = propertyName;
    }

}

class Person {
    //observable
    public Event<PropertyChangedEventArgs> propertyChanged = new Event<>();
    private int age;

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        if (this.age == age) return;

        this.age = age;
        propertyChanged.fire(new PropertyChangedEventArgs(this, "age"));

        if(canVote()) {
            propertyChanged.fire(new PropertyChangedEventArgs(this, "canVote"));
        }
    }

    public boolean canVote()
    {
        return this.age >= 18;
    }
}
public class ObserverExample {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Event<PropertyChangedEventArgs>.Subscription<PropertyChangedEventArgs> sub =
                person.propertyChanged.addSubscriber(x -> {
                    System.out.println("Person's "
                            + x.propertyName + " has changed");
                });
        person.setAge(17);
        person.setAge(18);
        sub.close();
        person.setAge(19);
    }
}
