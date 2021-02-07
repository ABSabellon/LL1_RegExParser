package core.lexer;

import core.exception.TokenSource;
import core.exception.UnknownCharacterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static core.lexer.TokenType.*;
/**
 * LEXER
 *
 * ALPHANUM -> [a-z0-9]
 * EPSILON -> 'E'
 * OPERATIONS -> '?', '*', '+'
 * UNION -> 'U'
 * LP -> '('
 * RP -> ')'
 * */

/**
 * scanner.Scanner class for the calculator. Parses the input string, identifying valid tokens.
 */
public class Scanner { //implements TokenSource {
    /**
     * Input string to be parsed
     */
    private final String input;

    /**
     * Starting position of the next lexeme
     */
    private int start;

    /**
     * Current character position to be consumed in the string
     */
    private int current;

    /**
     * List of scanned tokens
     */
    private final List<Token> scannedTokens;

    public Scanner(String input) {
        this.input = input;
        this.start = 0;
        this.current = 0;
        this.scannedTokens = new ArrayList<>();
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        return scannedTokens;
    }

    private void scanToken() {
        char ch = advance();

        switch (ch) {
            // skip all whitespace characters
            case ' ':
            case '\t':
            case '\r':
            case '\n':
                addToken(DELIMITER);
                break;

            case '?':
                addToken(OPTIONAL);
                break;

            case '*':
                addToken(ZERO_OR_MANY);
                break;

            case '+':
                addToken(ONE_OR_MANY);
                break;

            case 'E':
                addToken(EMPTY);
                break;

            case 'U':
                addToken(UNION);
                break;

            case '(':
                addToken(LEFT_PAR);
                break;

            case ')':
                addToken(RIGHT_PAR);
                break;
            default:
                if (Character.isDigit(ch) || Character.isLowerCase(ch)) {
                    addToken(ALPHANUM); //a-z0-9???
                }
                else {
                    throw new UnknownCharacterException(input.charAt(current - 1));
                }
        }
    }

    private boolean isAtEnd() {
        return current >= input.length();
    }

//    public Token nextToken() throws IOException { }

    private char advance() {
        return input.charAt(current++);
    }

    private char peek() {
        return input.charAt(current);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object value) {
        scannedTokens.add(newToken(type, value));
    }

    private Token newToken(TokenType type, Object value) {
        return new Token(type, getCurrentSubstring(), value);
    }

    private String getCurrentSubstring() {
        return input.substring(start, current);
    }
}
