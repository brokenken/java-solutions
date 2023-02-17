package expression.exceptions;

import expression.Add;
import expression.PriorityExpression;

public class CheckedAdd extends Add {

    public CheckedAdd(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    protected static boolean overflow(int firstOperand, int secondOperand) {
        if (firstOperand > 0) {
            return Integer.MAX_VALUE - firstOperand < secondOperand;
        } else {
            return Integer.MIN_VALUE - firstOperand > secondOperand;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException{
        int firstOperand = firstExpr.evaluate(x, y, z);
        int secondOperand = secondExpr.evaluate(x, y, z);
        if (overflow(firstOperand, secondOperand)) {
            throw new OverflowException("add");
        } else {
            return firstOperand + secondOperand;
        }
    }
}
