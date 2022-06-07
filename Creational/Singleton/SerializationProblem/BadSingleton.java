package Creational.Singleton.SerializationProblem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BadSingleton {

    private static final BadSingleton INSTANCE = new BadSingleton();
    private BadSingleton() {

    }

    protected Object readResolve() {
        return INSTANCE;
    }

    static void saveToFile(BadSingleton singleton, String fileName) throws Exception{

        try  {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    static BadSingleton readFromFile(String fileName) throws Exception {
        try {
            final FileInputStream fileInputStream = new FileInputStream(fileName);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            return (BadSingleton) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static BadSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        final BadSingleton badSingleton = BadSingleton.getInstance();
        String fileName = "singleton.bin";
        saveToFile(badSingleton, fileName);

        final BadSingleton badSingleton1 = readFromFile(fileName);

        System.out.println(badSingleton == badSingleton1); // return false, duplicated singleton instance.

    }
}
