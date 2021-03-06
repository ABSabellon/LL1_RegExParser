package core.lexer;

/**
 * A scanned token from the input string. Holds the token type, the input lexeme, and its value (if any)
 */
public class Token {
    /**
     * Type of token
     */
    public final TokenType type;

    /**
     * Original lexeme
     */
    private final String lexeme;

    /**
     * Token's value (for NUMBER only)
     */
    private final Object value;

    public static final int INVALID_TYPE = 0;
    public static final int EOF_TYPE = -1;

    public Token(TokenType type, String lexeme, Object value) {
        this.type = type;
        this.lexeme = lexeme;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "type = " + type + ", lexeme = " + lexeme;
    }
}
