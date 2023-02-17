package expression;

import java.util.Objects;

public class Negate implements PriorityExpression{
    protected final PriorityExpression expression;

    public Negate(PriorityExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() >= 5) {
            return "- " + expression.toMiniString();
        } else {
            return "-(" + expression.toMiniString() + ")";
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negate that = (Negate) o;
        return expression.equals(that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
