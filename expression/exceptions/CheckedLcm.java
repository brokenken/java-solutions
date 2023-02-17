package expression.exceptions;

import expression.Lcm;
import expression.PriorityExpression;

import static expression.Gcd.gcd;

public class CheckedLcm extends Lcm {
    public CheckedLcm(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }
    protected static boolean overflow(int firstOperand, int secondOperand) {
        int curr = gcd(firstOperand, secondOperand);
        if (CheckedDivide.overflow(firstOperand, curr)) {
            return true;
        }
        curr = firstOperand / curr;
        return CheckedMultiply.overflow(curr, secondOperand);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException {
        int firstOperand = firstExpr.evaluate(x, y, z);
        int secondOperand = secondExpr.evaluate(x, y, z);
        if (gcd(firstOperand, secondOperand) == 0) {
            return 0;
        }
        if (overflow(firstOperand, secondOperand)) {
            throw new OverflowException("LCM");
        } else {
            return (firstOperand / gcd(firstOperand, secondOperand)) * secondOperand;
        }
    }
}
