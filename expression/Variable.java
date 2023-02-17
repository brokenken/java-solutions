package expression;

import java.util.Objects;

public class Variable implements PriorityExpression{
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int evaluate(int x, int y, int z) {
        return switch (value) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException("Wrong variable");
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == Variable.class) {
            final Variable tmp = (Variable) obj;
            return value.equals(tmp.toMiniString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public String toMiniString() {
        return value;
    }
}
