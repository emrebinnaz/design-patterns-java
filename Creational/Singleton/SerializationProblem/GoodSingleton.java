package Creational.Singleton.SerializationProblem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GoodSingleton {

    private static final GoodSingleton INSTANCE = new GoodSingleton();
    private GoodSingleton() {

    }

    protected Object readResolve() {
        return INSTANCE; // Thanks to this function, when a serialization occurs, same instance returns. Thus Singleton pattern does not ruin.
    }

    static void saveToFile(GoodSingleton singleton, String fileName) throws Exception{

        try  {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    static GoodSingleton readFromFile(String fileName) throws Exception {
        try {
            final FileInputStream fileInputStream = new FileInputStream(fileName);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            return (GoodSingleton) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static GoodSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        final GoodSingleton goodSingleton = GoodSingleton.getInstance();
        String fileName = "singleton.bin";
        saveToFile(goodSingleton, fileName);

        final GoodSingleton goodSingleton1 = readFromFile(fileName);

        System.out.println(goodSingleton == goodSingleton1); // return true

    }
}
