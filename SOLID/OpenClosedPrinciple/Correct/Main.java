package SOLID.OpenClosedPrinciple.Correct;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final Product apple = new Product("apple", Color.GREEN, Size.SMALL);
        final Product tree = new Product("tree", Color.GREEN, Size.LARGE);
        final Product house = new Product("house", Color.BLUE, Size.LARGE);

        List<Product> productList = List.of(apple, tree, house);

        BetterFilter betterFilter = new BetterFilter();
        betterFilter.filter(productList, new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println(p.getName()));

        betterFilter.filter(productList, new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)))
                .forEach(p -> System.out.println(p.getName()));
    }
}
