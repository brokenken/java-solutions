package expression.exceptions;

public class OverflowException extends ArithmeticException{
    public OverflowException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Overflow in operation " + super.getMessage();
    }
}
