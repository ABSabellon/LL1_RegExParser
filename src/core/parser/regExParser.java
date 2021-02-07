package core.parser;

import core.exception.TokenSource;
import java.io.IOException;

    /**
     *  This parser follows LL(1) Parser:
     *
     * start -> term | EPSILON | LP start | oper
     * term ->  ALPHANUM oper
     * oper -> OPERATIONS prod | withUnion | ε
     * withUnion -> UNION factor
     * factor -> start | EPSILON
     * prod-> start | withUnion | ε
     */

public class regExParser extends Parser{
        public ExprParser(TokenSource input) throws IOException {
            super(input);
        }

    /** start -> term | EPSILON | LP start | oper */
    public void start(){
        if(lookahead() == ){

        }
        term();

    }
    /** term ->  ALPHANUM oper */
    public void term(){
       /* if(lookahead() == token.ALPHANUM){

        }*/

    }

    /** oper -> OPERATIONS prod | withUnion | ε */
    public void oper(){

    }

    /** withUnion -> UNION factor */
    public void withUnion(){

    }

    /** factor -> start | EPSILON */
    public void factor(){

    }

    /** prod-> start | withUnion | ε */
    public void prod(){

    }


}
