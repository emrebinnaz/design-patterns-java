package Behavioral.Visitor.Classic;

interface ExpressionVisitor {
    //any visitor can implement it.

    void visit(DoubleExpression doubleExpression);
    void visit(AdditionExpression additionExpression);

}
abstract class Expression {
    public abstract void accept(ExpressionVisitor visitor);
}
class DoubleExpression extends Expression{

    public double value;

    public DoubleExpression(double value)
    {
        this.value = value;
    }

    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }
}
class AdditionExpression extends Expression {

    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
class ExpressionPrinter implements ExpressionVisitor {

    private StringBuilder sb = new StringBuilder();
    //Polymorphism
    @Override
    public void visit(DoubleExpression doubleExpression) {
        sb.append(doubleExpression.value);
    }

    @Override
    public void visit(AdditionExpression additionExpression) {

        sb.append("(");
        additionExpression.left.accept(this); // in order to "visit" function
        sb.append("+");
        additionExpression.right.accept(this);
        sb.append(")");
    }

    @Override
    public String toString()
    {
        return sb.toString();
    }
}
//lets implement another visitor
class ExpressionCalculator implements ExpressionVisitor {

    public double result;

    @Override
    public void visit(DoubleExpression doubleExpression) {
        result = doubleExpression.value;
    }

    @Override
    public void visit(AdditionExpression additionExpression) {

        additionExpression.left.accept(this);
        double a = result;
        additionExpression.right.accept(this);
        double b = result;
        result = a - b;
    }
}
public class ClassicVisitor {
    public static void main(String[] args) {

        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                ));
        ExpressionPrinter ep = new ExpressionPrinter();
        ep.visit(e);
        System.out.println(ep);

        ExpressionCalculator calc = new ExpressionCalculator();
        calc.visit(e);
        System.out.println(ep + " = " + calc.result);

        //There is one pitfall of classic visitor. Each implementation has to visit DoubleExpression and AdditionalExpression.
        //Our ExpressionVisitor class is tightly coupled to 2 visit function. We can use Interface Segregation in order to solve this problem.
        //(Acyclic Visitor)
    }
}
