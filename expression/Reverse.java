package expression;

import java.util.Objects;

public class Reverse implements PriorityExpression{
    protected final PriorityExpression expression;

    public Reverse(PriorityExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "reverse(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() >= 5) {
            return "reverse " + expression.toMiniString();
        } else {
            return "reverse(" + expression.toMiniString() + ")";
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int num = expression.evaluate(x, y, z), rev = 0;
        while (num != 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;
            num /= 10;
        }
        return rev;
    }

    @Override
    public int getPriority() {
        return 9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reverse reverse = (Reverse) o;
        return expression.equals(reverse.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
