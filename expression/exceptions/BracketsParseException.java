package expression.exceptions;

public class BracketsParseException extends ParsingException{
    public BracketsParseException() {
        super("");
    }

    @Override
    public String getMessage() {
        return "Incorrect bracket sequence";
    }
}
