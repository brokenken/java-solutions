package expression;

public class Subtract extends AbstractOperation {
    public Subtract(PriorityExpression first, PriorityExpression second) {
        super(first, second, "-", 2);
    }


    @Override
    public String toMiniString() {
        StringBuilder operation = new StringBuilder();
        parsePriority(firstExpr, operation, 1);
        operation.append(" - ");
        parsePriority(secondExpr, operation, 3);
        return operation.toString();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return firstExpr.evaluate(x, y, z) - secondExpr.evaluate(x, y, z);
    }
}
