package expression.exceptions;

import expression.PriorityExpression;

import java.util.Objects;

public class CheckedPow10 implements PriorityExpression {
    protected final PriorityExpression expression;

    public CheckedPow10(PriorityExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "pow10(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() >= 5) {
            return "pow10 " + expression.toMiniString();
        } else {
            return "pow10(" + expression.toMiniString() + ")";
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws FunctionInputException, OverflowException {
        int val = expression.evaluate(x, y, z);
        int ans = 1;
        if (val >= 0) {
            while (val > 0) {
                val--;
                if (Integer.MAX_VALUE / 10 < ans) {
                    throw new OverflowException("pow10");
                }
                ans *= 10;
            }
            return ans;
        } else {
            throw new FunctionInputException("pow10");
        }
    }

    @Override
    public int getPriority() {
        return 11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedPow10 checkedPow10 = (CheckedPow10) o;
        return expression.equals(checkedPow10.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

}
