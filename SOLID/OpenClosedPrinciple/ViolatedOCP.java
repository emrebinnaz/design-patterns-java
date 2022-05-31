package SOLID.OpenClosedPrinciple;

import java.util.List;
import java.util.stream.Stream;

public class ViolatedOCP {
    enum Color {
        RED,
        GREEN,
        BLUE
    }

    enum Size{
        SMALL,
        MEDIUM,
        LARGE
    }
    class Product {
        private String name;
        private Color color;
        private Size size;

        public Product(String name, Color color, Size size) {
            this.name = name;
            this.color = color;
            this.size = size;
        }
    }

    class ProductFilter {
        public Stream<Product> filterByColor(List<Product> productList, Color color) {
            return productList.stream().filter(p -> p.color == color);
        }

        public Stream<Product> filterBySize(List<Product> productList, Size size) {
            return productList.stream().filter(p -> p.size == size);
        }

        public Stream<Product> filterBySizeAndColor(List<Product> productList, Size size, Color color) {
            return productList.stream().filter(p -> p.size == size && p.color == color);
        }
        // As the filtering conditions increase, the number of functions increases.
    }

}

