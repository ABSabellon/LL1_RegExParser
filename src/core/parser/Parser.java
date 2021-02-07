package core.parser;

public class Parser {
/*
    protected TokenSource input = null;

    protected Token[] tokens = null;

    protected int p = -1;

    public Parser(TokenSource input) throws IOException {
        this.input = input;
        initTokenBuffer(input);
    }

    protected void initTokenBuffer(TokenSource input) throws IOException {
        // fill token buffer to make lookahead easy
        List<Token> tokenBuf = new ArrayList<Token>();
        Token t;
        do {
            t = input.nextToken();
            tokenBuf.add(t);
        } while ( t.type!=Token.EOF_TYPE ); // add the end of file to the buffer
        tokens = new Token[tokenBuf.size()];
        tokenBuf.toArray(tokens);
        p = 0; // point at first token
    }

    protected void consume() {
        p++;
    }

    //lookahead -
    protected int lookahead() {
        if ( p>=tokens.length ) {
            return Token.EOF_TYPE;
        }
        return tokens[p].getType(); //get current Tokens
    }

    protected Token token() {
        return tokens[p];
    }

    protected void match(int type) {
        if ( tokens[p].type!=type ) {
            throw new RuntimeException("REJECTED"+
                    token().getLexeme());
        }
        consume();
    }*/

}
