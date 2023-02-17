package expression.exceptions;

import expression.Negate;
import expression.PriorityExpression;

public class CheckedNegate extends Negate {
    public CheckedNegate(PriorityExpression expression) {
        super(expression);
    }

    private boolean overflow(int operand) {
        return Integer.MIN_VALUE == operand;
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException {
        int operand = expression.evaluate(x, y, z);
        if (overflow(operand)) {
            throw new OverflowException("negate");
        }
        return -operand;
    }
}
