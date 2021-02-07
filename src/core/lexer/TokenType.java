package core.lexer;

public enum TokenType {
    DELIMITER,
    OPTIONAL,
    ZERO_OR_MANY,
    ONE_OR_MANY,
    EPSILON,
    UNION,
    LEFT_PAR,
    RIGHT_PAR,
    ALPHANUM,
    REJECT
}
