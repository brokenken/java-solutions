package expression.exceptions;

public class DivisionByZeroException extends ArithmeticException{

    public DivisionByZeroException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Division by zero" + super.getMessage();
    }
}
