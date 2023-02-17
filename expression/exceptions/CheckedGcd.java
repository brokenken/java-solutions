package expression.exceptions;

import expression.Gcd;
import expression.PriorityExpression;

public class CheckedGcd extends Gcd{
    public CheckedGcd(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }
}
