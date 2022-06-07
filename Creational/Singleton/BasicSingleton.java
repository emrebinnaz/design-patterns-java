package Creational.Singleton;

public class BasicSingleton {

    private static final BasicSingleton INSTANCE = new BasicSingleton();
    private BasicSingleton() {

    }

    public BasicSingleton getInstance() {
        return INSTANCE;
    }
}
