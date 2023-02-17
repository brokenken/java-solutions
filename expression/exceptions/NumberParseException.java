package expression.exceptions;

public class NumberParseException extends ParsingException{
    public NumberParseException() {
        super("");
    }

    @Override
    public String getMessage() {
        return "Constant overflow";
    }
}
