package Structural.Adapter.Exercise;

class Square implements Rectangle
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }

    @Override
    public int getWidth() {
        return side;
    }

    @Override
    public int getHeight() {
        return side;
    }
    @Override
    public int getArea() {
        return side * side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{
    Square square;
    public SquareToRectangleAdapter(Square square)
    {
        this.square = square;
    }

    @Override
    public int getWidth() {
        return square.getWidth();
    }

    @Override
    public int getHeight() {
        return square.getHeight();
    }

    @Override
    public int getArea() {
        return this.square.getArea();
    }
}
class Demo {
    public static void main(String[] args) {
        SquareToRectangleAdapter adapter = new SquareToRectangleAdapter(new Square(2));
    }
}