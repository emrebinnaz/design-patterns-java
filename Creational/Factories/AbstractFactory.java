package Creational.Factories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AbstractFactory {

    interface HotDrink {
        void consume();
    }

    class Tea implements HotDrink {

        @Override
        public void consume() {
            System.out.println("This tea is nice but I'd prefer it with milk.");
        }
    }

    class Coffee implements HotDrink {

        @Override
        public void consume() {
            System.out.println("This coffee is delicious");
        }
    }

    interface HotDrinkFactory {
        HotDrink prepare(int amount);
    }

    class TeaFactory implements HotDrinkFactory {

        @Override
        public HotDrink prepare(int amount) {

            System.out.println(
                    "Put in tea bag, boil water, pour "
                            + amount + "ml, add lemon, enjoy!"
            );
            return new Tea();
        }
    }

    class CoffeeFactory implements HotDrinkFactory {

        @Override
        public HotDrink prepare(int amount) {
            System.out.println(
                    "Grind some beans, boil water, pour "
                            + amount + " ml, add cream and sugar, enjoy!"
            );
            return new Coffee();
        }
    }

    static class HotDrinkMachine  {
        public enum AvailableDrink {
            COFFEE,
            TEA
        }
        private Map<AvailableDrink, HotDrinkFactory> factoryMap = new HashMap<>();

        public HotDrinkMachine() throws Exception {
            for (AvailableDrink drink: AvailableDrink.values()) {
                final String s = drink.toString();
                String factoryName = "" + Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
                Class<?> factory = Class.forName("design-patterns-java.Creational.Factories." + factoryName + "Factory");
                factoryMap.put(drink, (HotDrinkFactory) factory.getDeclaredConstructor().newInstance());

            }
        }

        public HotDrink makeDrink() throws IOException {
            System.out.println("Available drinks");
            factoryMap.forEach((key, value) -> key.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true)
            {
                String s;
                int i, amount;
                if ((s = reader.readLine()) != null
                        && (i = Integer.parseInt(s)) >= 0
                        && i < factoryMap.size())
                {
                    System.out.println("Specify amount: ");
                    s = reader.readLine();
                    if (s != null
                            && (amount = Integer.parseInt(s)) > 0)
                    {
                        return factoryMap.get(i).prepare(amount);
                    }
                }
                System.out.println("Incorrect input, try again.");
            }
        }

        public HotDrink makeDrink(AvailableDrink availableDrink, int amount) {
            return ((HotDrinkFactory)factoryMap.get(availableDrink)).prepare(amount);
        }
    }

    static class Demo {

        public static void main(String[] args) throws Exception {
            HotDrinkMachine machine = new HotDrinkMachine();
            HotDrink tea = machine.makeDrink(HotDrinkMachine.AvailableDrink.TEA, 200);
            tea.consume();
        }
    }
}
