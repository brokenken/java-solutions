package expression.exceptions;

import expression.PriorityExpression;

import java.util.Objects;

public class CheckedLog10 implements PriorityExpression {
    protected final PriorityExpression expression;

    public CheckedLog10(PriorityExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "log10(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() >= 5) {
            return "log10 " + expression.toMiniString();
        } else {
            return "log10(" + expression.toMiniString() + ")";
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws FunctionInputException{
        int val = expression.evaluate(x, y, z);
        int ans = 0;
        if (val <= 0) {
            throw new FunctionInputException("log10");
        }
        while (val >= 10) {
            val /= 10;
            ans += 1;
        }
        return ans;
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedLog10 checkedLog10 = (CheckedLog10) o;
        return expression.equals(checkedLog10.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

}
