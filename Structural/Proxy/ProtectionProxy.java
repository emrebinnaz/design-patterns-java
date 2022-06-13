package Structural.Proxy;

interface Drivable {
    void drive();
}
class Car implements Drivable {

    private Driver driver;

    public Car(Driver driver)
    {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}

class Driver
{
    public int age;

    public Driver(int age)
    {
        this.age = age;
    }
}

class CarProxy extends Car{
    private Driver driver;

    public CarProxy(Driver driver) {
        super(driver);
    }
    @Override
    public void drive()
    {
        if (driver.age >= 17)
            super.drive();
        else
            System.out.println("Driver too young");
    }
}
public class ProtectionProxy {
    public static void main(String[] args) {

        Drivable car = new CarProxy(new Driver(12));
        car.drive(); // Driver too young

    }
}
