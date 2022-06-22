package Behavioral.Visitor.Intrusive;

// bad example

abstract class Expression {
    // like 1 + 3
    public abstract void print(StringBuilder sb);
    // If we want to change "print" function, We have to modify "print" function of each child class, so it violates OCP.

}

class DoubleExpression extends Expression {

    private double value;

    public DoubleExpression(double value)
    {
        this.value = value;
    }

    @Override
    public void print(StringBuilder sb) {
        sb.append(value);
    }
}

class AdditionExpression extends Expression {

    private Expression left, right;
    public AdditionExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }
    @Override
    public void print(StringBuilder sb) {

        sb.append("(");
        left.print(sb);
        sb.append("+");
        right.print(sb);
        sb.append(")");
    }
}
public class IntrusiveVisitor {
    public static void main(String[] args) {
        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                ));
        StringBuilder sb = new StringBuilder();
        e.print(sb);
        System.out.println(sb);
    }
}
