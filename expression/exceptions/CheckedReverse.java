package expression.exceptions;

import expression.PriorityExpression;
import expression.Reverse;

public class CheckedReverse extends Reverse {
    public CheckedReverse(PriorityExpression expression) {
        super(expression);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException{
        int num = expression.evaluate(x, y, z);
        StringBuilder rev = new StringBuilder();
        if (num == Integer.MIN_VALUE) {
            throw new OverflowException("reverse");
        }
        if (num < 0) {
            rev.append("-");
            num = -num;
        }
        if (num == 0) {
            rev.append(0);
        }
        while (num != 0) {
            rev.append(num % 10);
            num /= 10;
        }
        try {
            return Integer.parseInt(rev.toString());
        } catch (NumberFormatException e) {
            throw new OverflowException("reverse");
        }
    }
}
