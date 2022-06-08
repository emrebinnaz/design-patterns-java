package Structural.Bridge.Exercise;

public class Demo {
    abstract class Shape
    {
        public Renderer renderer;
        public abstract String getName();

        public Shape(Renderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public String toString() {
            return "Drawing " + getName() + " as " + renderer.whatToRenderAs();
        }
    }

    class Triangle extends Shape
    {
        Renderer renderer;

        public Triangle(Renderer renderer) {
            super(renderer);
        }

        @Override
        public String getName()
        {
            return "Triangle";
        }
    }

    class Square extends Shape
    {
        Renderer renderer;
        public Square(Renderer renderer) {
            super(renderer);
        }

        @Override
        public String getName()
        {
            return "Square";
        }

    }

    class VectorRenderer implements Renderer
    {

        @Override
        public String whatToRenderAs() {
            return "lines";
        }
    }

    class RasterRenderer implements Renderer
    {
        @Override
        public String whatToRenderAs() {
            return "pixels";
        }
    }

    interface Renderer {
        String whatToRenderAs();
    }
}
