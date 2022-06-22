package Behavioral.Visitor.Acyclic;


interface Visitor {} // base interface

interface ExpressionVisitor extends Visitor {
    void visit(Expression expression);
}

interface DoubleExpressionVisitor extends Visitor {
    void visit(DoubleExpression doubleExpression);
}

interface AdditionExpressionVisitor extends Visitor {
    void visit(AdditionExpression additionExpressionVisitor);
}

abstract class Expression {

    public void accept(Visitor visitor) {
        //it is optional
        // we can use factory method instead of "intanceof" keyword in order to appropriate "visit" function.
        if( visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor) visitor).visit(this);
        }
    }
}

class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof DoubleExpressionVisitor) {
            ((DoubleExpressionVisitor) visitor).visit(this);
        }
    }
}

class AdditionExpression extends Expression
{
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof AdditionExpressionVisitor) {
            ((AdditionExpressionVisitor) visitor).visit(this);
        }
    }
}
class ExpressionPrinter implements DoubleExpressionVisitor, AdditionExpressionVisitor {
    //Even if we delete one of the interfaces, expression printer can print although some parts missing.
    // Interface Segregation Principle can provide more flexibility.

    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpression doubleExpression) {
        sb.append(doubleExpression.value);
    }

    @Override
    public void visit(AdditionExpression additionExpression) {
        sb.append('(');
        additionExpression.left.accept(this);
        sb.append('+');
        additionExpression.right.accept(this);
        sb.append(')');
    }
    @Override
    public String toString() {
        return sb.toString();
    }
}

public class AcyclicVisitor {
    public static void main(String[] args) {

        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                )
        );
        ExpressionPrinter ep = new ExpressionPrinter();
        ep.visit(e);
        System.out.println(ep.toString());
    }
}
