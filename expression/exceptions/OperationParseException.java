package expression.exceptions;

public class OperationParseException extends ParsingException{

    public OperationParseException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Expected operation at pos: " + super.getMessage();
    }
}
