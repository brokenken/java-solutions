package expression.exceptions;

import expression.Multiply;
import expression.PriorityExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    protected static boolean overflow(int firstOperand, int secondOperand) {
        int res = firstOperand * secondOperand;
        return firstOperand != 0 && secondOperand != 0 && (res / secondOperand != firstOperand || res / firstOperand != secondOperand);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException {
        int firstOperand = firstExpr.evaluate(x, y, z);
        int secondOperand = secondExpr.evaluate(x, y, z);
        if (overflow(firstOperand, secondOperand)) {
            throw new OverflowException("multiply");
        } else {
            return firstOperand * secondOperand;
        }
    }
}
