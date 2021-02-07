package core.parser;

import core.lexer.Token;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    protected ArrayList<ArrayList<Token>> lines;

    public Parser(){
        lines = new ArrayList<ArrayList<Token>>();
    }

    public void addTokenLine(List<Token> scannedTokens) {
        this.lines.add((ArrayList<Token>) scannedTokens); //typecasting
    }

    public void printList(){
        for(ArrayList<Token> line : lines){
            System.out.println(line);
        }
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
