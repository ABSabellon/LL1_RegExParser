package core.lexer;

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

    //The switch case below is based in a FSM
    private void scanToken() {
        char ch = advance();

        switch (ch) {
            // skip all whitespace characters
            case ' ':
            case '\t':
            case '\r':
            case '\n':
                addToken(DELIMITER); //State 1
                break;

            case '?':
                addToken(OPTIONAL); //State 2
                break;

            case '*':
                addToken(ZERO_OR_MANY); //State 3
                break;

            case '+':
                addToken(ONE_OR_MANY); //State 4
                break;

            case 'E':
                addToken(EPSILON); //State 5
                break;

            case 'U':
                addToken(UNION); //State 6
                break;

            case '(':
                addToken(LEFT_PAR); //State 7
                break;

            case ')':
                addToken(RIGHT_PAR); //State 8
                break;
            default:
                if (Character.isDigit(ch) || Character.isLowerCase(ch)) {
                    addToken(ALPHANUM); //a-z0-9??? State 9
                }
                else {
                    addToken(REJECT); // ETC State 10
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
