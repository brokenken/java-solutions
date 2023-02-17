package expression;

import static java.lang.Math.abs;

public class Gcd extends AbstractOperation{
    public Gcd(PriorityExpression first, PriorityExpression second) {
        super(first, second, "gcd", -1);
    }

    public static int gcd(int a, int b) {
        return abs(b == 0 ? a : gcd(b, a % b));
    }
    @Override
    public String toMiniString() {
        StringBuilder operation = new StringBuilder();
        parsePriority(firstExpr, operation, -4);
        operation.append(" gcd ");
        parseTwoPriority(secondExpr, operation, -3, -1);
        return operation.toString();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return gcd(firstExpr.evaluate(x, y, z), secondExpr.evaluate(x, y, z));
    }
}
