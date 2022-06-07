package Creational.Singleton;

public class LazySingleton {

    private static LazySingleton singleton;
    private LazySingleton() {

    }
    public static LazySingleton getInstance() {
        if(singleton == null) {
            singleton = new LazySingleton();
            //potential race condition.
            // put synchronized keyword for thread-safe singleton
        }
        return singleton;
    }
}
