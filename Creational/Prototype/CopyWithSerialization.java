package Creational.Prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Person implements Serializable{
    public String name;
    public String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name=" + name +
                ", surname='" + surname + '\'' +
                '}';
    }
}

public class CopyWithSerialization {

    public static void main(String[] args) {
        Person p = new Person("Emre", "Binnaz");
        final Person copiedPerson = SerializationUtils.roundtrip(p);
        // It is a kind of copy by value.
    }
}
