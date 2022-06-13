package Structural.Proxy.Exercise;

class Person
{
    private int age;

    public Person(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String drink() { return "drinking"; }
    public String drive() { return "driving"; }
    public String drinkAndDrive() { return "driving while drunk"; }
}

class ResponsiblePerson extends Person
{
    private Person person;

    public ResponsiblePerson(Person person)
    {
        super(person.getAge());
        this.person = person;
        // todo
    }

    @Override
    public void setAge(int age) {
        this.person.setAge(age);
    }

    @Override
    public String drink() {
        if(this.person.getAge() < 18){
            return "too young";
        }
        return super.drink();
    }

    @Override
    public String drive() {
        if(this.person.getAge() < 16) {
            return "too young";
        }
        return super.drive();
    }

    @Override
    public String drinkAndDrive() {
        return "dead";
    }
}

public class Demo {
    public static void main(String[] args) {

        Person p = new Person(10);
        ResponsiblePerson rp = new ResponsiblePerson(p);
        System.out.println(rp.drive());
        System.out.println(rp.drink());
        System.out.println(rp.drinkAndDrive());


        rp.setAge(20);

        System.out.println(rp.drive());
        System.out.println(rp.drink());
        System.out.println(rp.drinkAndDrive());

    }
}
