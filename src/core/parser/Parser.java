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

    public Parser(String l){
//        for(Token token: scannedTokens){
//            if(token.getType() != TokenType.DELIMITER){
//                tokens.add(token);
//            }
//        }

        line = l.toCharArray();
    }

    public char lookahead(int index){
        return line[index+1];
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
