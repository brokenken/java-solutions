package expression.exceptions;

public class FunctionInputException extends ArithmeticException{
    public FunctionInputException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Incorrect function argument in " + super.getMessage();
    }
}
