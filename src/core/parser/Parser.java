package core.parser;

import core.lexer.Token;
import core.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    char[] line;
    private int parenthesisStack = 0;
    public boolean parseEval = true;
    public String parseEvalString = "ACCEPTED";
    public static int index;

    public Parser(List<Token> scannedTokens){
        for(Token token: scannedTokens){
            if(token.getType() != TokenType.DELIMITER){
                tokens.add(token);
            }
        }
        index = 0;
    }

    public Token lookahead(int index){
        if((index+1) < tokens.size()){
            return tokens.get(index+1);
        }
        else{
            return null;
        }
    }

    public char lookbehind(int index){
        return line[index-1];
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
}
