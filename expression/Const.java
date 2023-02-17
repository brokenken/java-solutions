package expression;

import java.util.Objects;

public class Const implements PriorityExpression{
    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toMiniString() {
        return value.toString();
    }

    @Override
    public int getPriority() {
        return 5;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == Const.class) {
            final Const tmp = (Const) obj;
            return value.equals(tmp.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
