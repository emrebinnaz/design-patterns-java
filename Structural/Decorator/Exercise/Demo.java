package Structural.Decorator.Exercise;

interface Flying {
    String fly();
}
interface Crawling {
    String crawl();
}
class Bird implements Flying
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }

}

class Lizard implements Crawling
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }


}
class Dragon implements Flying, Crawling {
    private int age;
    private Bird bird = new Bird();
    private Lizard lizard = new Lizard();
    public void setAge(int age)
    {
        this.age = bird.age = lizard.age = age;
    }
    public String fly()
    {
        return bird.fly();
    }
    public String crawl()
    {
        return lizard.crawl();
    }
}

public class Demo {
    Dragon dragon = new Dragon();

    public static void main(String[] args) {
        System.out.println(new Dragon().fly());
    }
}
