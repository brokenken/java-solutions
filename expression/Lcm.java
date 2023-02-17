package expression;

import static expression.Gcd.gcd;

public class Lcm extends AbstractOperation{
    public Lcm(PriorityExpression first, PriorityExpression second) {
        super(first, second, "lcm", -2);
    }

    @Override
    public String toMiniString() {
        StringBuilder operation = new StringBuilder();
        parsePriority(firstExpr, operation, -4);
        operation.append(" lcm ");
        parseTwoPriority(secondExpr, operation, -2, 0);
        return operation.toString();
    }


    @Override
    public int evaluate(int x, int y, int z) {
        int a = firstExpr.evaluate(x, y, z);
        int b = secondExpr.evaluate(x, y, z);
        if (gcd(a, b) == 0) {
            return 0;
        }
        return (a / gcd(a, b)) * b;
    }
}
