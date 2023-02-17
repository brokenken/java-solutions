package expression;

public class Divide extends AbstractOperation{
    public Divide(PriorityExpression first, PriorityExpression second) {
        super(first, second, "/", 3);
    }

    @Override
    public String toMiniString() {
        StringBuilder operation = new StringBuilder();
        parsePriority(firstExpr, operation, 3);
        operation.append(" / ");
        parsePriority(secondExpr, operation, 5);
        return operation.toString();
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return firstExpr.evaluate(x, y, z) / secondExpr.evaluate(x, y, z);
    }
}
