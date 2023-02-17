package expression;

import java.util.Objects;

abstract public class AbstractOperation implements PriorityExpression{
    protected final PriorityExpression firstExpr;
    protected final PriorityExpression secondExpr;
    private final String operation;
    private final int priority;
    
    public AbstractOperation(PriorityExpression first, PriorityExpression second, String operation, int priority) {
        this.firstExpr = first;
        this.secondExpr = second;
        this.operation = operation;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    protected void parsePriority(PriorityExpression v, StringBuilder in, int prior) {
        if (v.getPriority() < prior) {
            in.append("(").append(v.toMiniString()).append(")");
        } else {
            in.append(v.toMiniString());
        }
    }

    protected void parseTwoPriority(PriorityExpression v, StringBuilder in, int prior1, int prior2) {
        if (v.getPriority() < prior2 && v.getPriority() > prior1) {
            in.append("(").append(v.toMiniString()).append(")");
        } else {
            in.append(v.toMiniString());
        }
    }

    public PriorityExpression getFirstExpr() {
        return firstExpr;
    }

    public PriorityExpression getSecondExpr() {
        return secondExpr;
    }

    @Override
    public String toString() {
        if (firstExpr != null && secondExpr != null) {
            return "(" + firstExpr.toString() + " " + operation + " " + secondExpr.toString() + ")";
        }
        throw new IllegalArgumentException("Null expression");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            final AbstractOperation tmp = (AbstractOperation) obj;
            ToMiniString left = tmp.getFirstExpr();
            ToMiniString right = tmp.getSecondExpr();
            return left.equals(firstExpr) && right.equals(secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstExpr, secondExpr, operation, priority);
    }
}
