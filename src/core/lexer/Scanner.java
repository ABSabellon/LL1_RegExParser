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
    /** Tokens are scanned and placed int a List*/
    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        return scannedTokens;
    }

    //The switch case below is based in an FSM
    private void scanToken() {
        int state = 0;// start state
        char ch = advance();

        switch (ch) {
            // skip all whitespace characters
            case ' ':
            case '\t':
            case '\r':
            case '\n':
                state = 1; //DELIMETERS
                break;

            case '?':
                state = 2; //OPTIONAL
                break;

            case '*':
                state = 3; //ZERO_OR_MANY
                break;

            case '+':
                state = 4; //ONE_OR_MANY
                break;

            case 'E':
                state = 5;; //EPSILON
                break;

            case 'U':
                state = 6;; //UNION
                break;

            case '(':
                state = 7;; //LEFT PARENTHESIS
                break;

            case ')':
                state = 8;; //RIGHT PARENTHESIS
                break;
            default:
                if (Character.isDigit(ch) || Character.isLowerCase(ch)) {
                    state = 9;; //a-z0-9??? ALPHANUM
                }
                else {
                    state = 10; // ETC / REJECTS
                }
        }
        // Place each Tokens to their respective types
        if(state == 1)
            addToken(DELIMITER);
        if(state == 2)
            addToken(OPTIONAL);
        if(state == 3)
            addToken(ZERO_OR_MANY);
        if(state == 4)
            addToken(ONE_OR_MANY);
        if(state == 5)
            addToken(EPSILON);
        if(state == 6)
            addToken(UNION);
        if(state == 7)
            addToken(LEFT_PAR);
        if(state == 8)
            addToken(RIGHT_PAR);
        if(state == 9)
            addToken(ALPHANUM);
        if(state == 10)
            addToken(REJECT);
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
