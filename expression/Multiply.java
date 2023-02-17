package expression;

public class Multiply extends AbstractOperation{
    public Multiply(PriorityExpression first, PriorityExpression second) {
        super(first, second, "*", 4);
    }

    @Override
    public String toMiniString() {
        StringBuilder operation = new StringBuilder();
        parsePriority(firstExpr, operation, 3);
        operation.append(" * ");
        parsePriority(secondExpr, operation, 4);
        return operation.toString();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return firstExpr.evaluate(x, y, z) * secondExpr.evaluate(x, y, z);
    }
}
