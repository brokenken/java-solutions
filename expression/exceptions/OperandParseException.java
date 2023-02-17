package expression.exceptions;

public class OperandParseException extends ParsingException{
    public OperandParseException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Expected operand at pos: " + super.getMessage();
    }
}
