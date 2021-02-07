package core.exception;

public class UnknownCharacterException extends RuntimeException {
    public UnknownCharacterException(char ch) {
        super("REJECTED. Offending token: " + ch);
    }
}
