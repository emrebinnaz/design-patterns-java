package Behavioral.Observer;

import java.util.ArrayList;
import java.util.List;

interface Notifable {
    void sendNotification(String to);
}

class EmailNotification implements Notifable {

    @Override
    public void sendNotification(String to) {
        System.out.println("Happy birthday " +  to + " ! \n  Sent by email" );
    }

}

class SmsNotification implements Notifable {

    @Override
    public void sendNotification(String to) {
        System.out.println("Happy birthday " +  to + " ! \n  Sent by sms" );
    }
}

class User {
    //Observer
    private String name;
    private int age;
    private List<Notifable> notificationChannels = new ArrayList<>(); // observers via Dependency Inversion

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        sendBirthdayMessage(this.notificationChannels);
    }

    private void sendBirthdayMessage(List<Notifable> notificationChannels) {
        for (Notifable notifable: notificationChannels) {
                notifable.sendNotification(getName());
        }
    }

    public void addNotificationChannel(Notifable notifable) {
        this.notificationChannels.add(notifable);
    }
    public void removeNotificationChannel(Notifable notifable) {
        this.notificationChannels.remove(notifable);
    }
}


public class NotificationExample {

    public static void main(String[] args) {
        User user = new User("Emre", 20);

        final Notifable email = new EmailNotification();
        final Notifable sms = new SmsNotification();

        user.addNotificationChannel(email);
        user.addNotificationChannel(sms);

        user.setAge(21);
    }
}
