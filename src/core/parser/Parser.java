package core.parser;

import core.lexer.Token;
import core.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    private int parenthesisStack = 0;

    public Parser(List<Token> scannedTokens){
        for(Token token: scannedTokens){
            if(token.getType() != TokenType.DELIMITER){
                tokens.add(token);
            }
        }
    }

    public void openParen(){
        parenthesisStack++;
    }

    public void closeParen(){
        parenthesisStack--;
    }

    public boolean isParenBalance(){
        return parenthesisStack == 0;
    }

    abstract String parseEval();

}
