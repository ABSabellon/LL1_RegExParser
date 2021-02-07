package core.parser;

/**
     *  This parser follows LL(1) Parser:
     *
     * start -> chars | oper | LP start RP | EPSILON
     * chars ->  ALPHANUM oper
     * oper -> OPERATIONS more | comb | ε
     * comb -> UNION factor
     * factor -> start | EPSILON
     * more-> start | comb | ε
     */

public class regExParser extends Parser{
    public regExParser() {
        super();
    }

    //** start -> chars | oper | LP start RP | EPSILON *//
    public void start(){
//        if(lookahead() == ){
//
//        }
//        term();

    }
    //** chars ->  ALPHANUM oper *//*
    public void chars(){
       //* if(lookahead() == token.ALPHANUM){

    }

    //** oper -> OPERATIONS more | comb | ε *//*
    public void oper(){

    }

    //** comb -> UNION factor *//*
    public void comb(){

    }

    //** factor -> start | EPSILON *//*
    public void factor(){

    }

    //** more-> start | comb | ε *//*
    public void more(){

    }


}
