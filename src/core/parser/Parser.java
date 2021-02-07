package core.parser;

import core.lexer.Token;
import core.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    private int parenthesisStack = 0;

    public Parser(List<Token> scannedTokens){
        for(Token token: scannedTokens){
            if(token.getType() != TokenType.DELIMITER){
                tokens.add(token);
            }
        }
    }
    public String getParsedString(){
        String parsed = "";
        for(Token token : this.tokens){
            parsed = parsed + token.getType() + " ";
        }
        parsed = parsed + "- " + this.parseEval() + "\n";
        return parsed;
    }

    private String parseEval(){
        for(Token token : this.tokens){
            if(token.getType() == TokenType.REJECT){
                return "REJECT";
            }
            else if(token.getType() == TokenType.LEFT_PAR){
                parenthesisStack++;
            }
            else if(token.getType() == TokenType.RIGHT_PAR){
                parenthesisStack--;
            }
        }

        if(parenthesisStack != 0){
            return "REJECT";
        }
        return "ACCEPT";
    }

//    protected void initTokenBuffer(TokenSource input) throws IOException {
//        // fill token buffer to make lookahead easy
//        List<Token> tokenBuf = new ArrayList<Token>();
//        Token t;
//        do {
//            t = input.nextToken();
//            tokenBuf.add(t);
//        } while ( t.type!=Token.EOF_TYPE ); // add the end of file to the buffer
//        tokens = new Token[tokenBuf.size()];
//        tokenBuf.toArray(tokens);
//        p = 0; // point at first token
//    }
//
//    protected void consume() {
//        p++;
//    }
//
//    //lookahead -
//    protected int lookahead() {
//        if ( p>=tokens.length ) {
//            return Token.EOF_TYPE;
//        }
//        return tokens[p].getType(); //get current Tokens
//    }
//
//    protected Token token() {
//        return tokens[p];
//    }
//
//    protected void match(int type) {
//        if ( tokens[p].type!=type ) {
//            throw new RuntimeException("REJECTED"+
//                    token().getLexeme());
//        }
//        consume();
//    }

}
