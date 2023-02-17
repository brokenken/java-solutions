package expression.exceptions;

import expression.Divide;
import expression.PriorityExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    protected static boolean overflow(int firstOperand, int secondOperand) {
        int res = firstOperand / secondOperand;
        if (CheckedMultiply.overflow(res, secondOperand)) {
            return true;
        }
        int k = res * secondOperand;
        return CheckedAdd.overflow(k, firstOperand % secondOperand);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException, DivisionByZeroException {
        int firstOperand = firstExpr.evaluate(x, y, z);
        int secondOperand = secondExpr.evaluate(x, y, z);
        if (secondOperand == 0) {
            throw new DivisionByZeroException("");
        }
        if (overflow(firstOperand, secondOperand)) {
            throw new OverflowException("divide");
        } else {
            return firstOperand / secondOperand;
        }
    }
}
