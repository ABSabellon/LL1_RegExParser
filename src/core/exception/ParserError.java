package core.exception;

public class ParserError extends Exception{
    public String getMessage() {
        return "REJECTED";
    }
}