package Creational.Factories.Exercise;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory
{
    private int createdPersonCount = 0;

    public Person createPerson(String name)
    {
        return new Person(createdPersonCount++, name);
    }
}
