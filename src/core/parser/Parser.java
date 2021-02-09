package core.parser;

import core.lexer.Token;
import core.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    public boolean parseEval = true;
    public String parseEvalString = "ACCEPTED";
    public int index;
    private Stack<Token> parenthesisStack;

    public Parser(List<Token> scannedTokens){
        for(Token token: scannedTokens){
            if(token.getType() != TokenType.DELIMITER){
                tokens.add(token);
            }
        }
        parenthesisStack = new Stack<Token>();
        index = 0;
    }
    /**
     * lookahead is used to check if the token matches the rule
     */
    public Token lookahead(int index){
        if((index+1) < tokens.size()){
            return tokens.get(index+1);
        }
        else{
            return null;
        }
    }
    /**
     * This solely used to check if it is the start of the string.
     */
    public Token startOfToken(int index){
        if(index > 0){
            return tokens.get(index-1);
        }
        else{
            return null;
        }
    }

    public void handleParen(Token tk){
        if(tk.getType() == TokenType.LEFT_PAR) {
            parenthesisStack.push(tk);
        }
        if(tk.getType() == TokenType.RIGHT_PAR) {
            parenthesisStack.pop();
        }
    }

    public boolean isParenBalance(){
        return parenthesisStack.empty();
    }
}
