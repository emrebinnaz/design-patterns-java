package Creational.Singleton;

public class InnerStaticSingleton {

    private InnerStaticSingleton() {}

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}
class Demo {
    public static void main(String[] args) {
        final InnerStaticSingleton instance = InnerStaticSingleton.getInstance();
        /*  The helper inner class is not loaded into memory when the singleton class is loaded into memory.
            It is loaded only when someone calls the getInstance method. So there is no need for synchronization.
            It solves synchronization problem. -> Bill Pugh Singleton
        * */
    }
}
