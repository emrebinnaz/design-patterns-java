package Behavioral.Interpreter.Exercise;

import java.util.ArrayList;
import java.util.List;

interface DepartmentExpression {
    void interpret(Context context);
}
class Context {
    private String formula;
    private int totalCost;

    public Context(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}

class HrExpression implements DepartmentExpression {

    @Override
    public void interpret(Context context) {
        context.setTotalCost(context.getTotalCost() + 20);
    }
}
class SoftwareExpression implements DepartmentExpression {

    @Override
    public void interpret(Context context) {
        context.setTotalCost(context.getTotalCost() + 30);
    }
}

class SalesExpression implements DepartmentExpression {

    @Override
    public void interpret(Context context) {
        context.setTotalCost(context.getTotalCost() + 30);
    }
}

public class Demo {
    static List<DepartmentExpression> createExpressionList(String formula) {
        List<DepartmentExpression> expressions = new ArrayList<>();
        for (int i = 0; i < formula.length(); i++) {
            if(formula.charAt(i) == 'S') {
                expressions.add(new SalesExpression());
            } else if(formula.charAt(i) == 'I') {
                expressions.add(new HrExpression());
            } else if(formula.charAt(i) == 'Y') {
                expressions.add(new SoftwareExpression());
                // Factory design pattern can be used in here.
            }
        }
        return expressions;

    }
    public static void main(String[] args) {
        final Context context = new Context("SIYSI"); // SIYSI is special text in order to interpret for our needs.
        final List<DepartmentExpression> expressionList = createExpressionList(context.getFormula());
        expressionList.forEach(departmentExpression -> departmentExpression.interpret(context));
        System.out.println(context.getTotalCost());


    }
}
