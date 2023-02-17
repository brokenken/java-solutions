package expression;

public class Add extends AbstractOperation{
    public Add(PriorityExpression first, PriorityExpression second) {
        super(first, second, "+", 1);
    }

    @Override
    public String toMiniString() {
        StringBuilder operation = new StringBuilder();
        parsePriority(firstExpr, operation, 1);
        operation.append(" + ");
        parsePriority(secondExpr, operation, 1);
        return operation.toString();
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return firstExpr.evaluate(x, y, z) + secondExpr.evaluate(x, y, z);
    }
}
