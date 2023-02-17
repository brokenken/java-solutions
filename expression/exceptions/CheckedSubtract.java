package expression.exceptions;

import expression.PriorityExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    private boolean overflow(int firstOperand, int secondOperand) {
        if (secondOperand > 0) {
            return Integer.MIN_VALUE + secondOperand > firstOperand;
        } else {
            return Integer.MAX_VALUE + secondOperand < firstOperand;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException {
        int firstOperand = firstExpr.evaluate(x, y, z);
        int secondOperand = secondExpr.evaluate(x, y, z);
        if (overflow(firstOperand, secondOperand)) {
            throw new OverflowException("subtract");
        } else {
            return firstOperand - secondOperand;
        }
    }
}
