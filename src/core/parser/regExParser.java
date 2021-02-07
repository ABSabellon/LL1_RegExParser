package core.parser;

import core.lexer.Token;

import java.util.List;

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
    public regExParser(List<Token> scannedTokens) {
        super(scannedTokens);
    }

    @Override
    public String parseEval() {
        return "REJECT";
    }

    //** start -> chars | oper | LP start RP | EPSILON *//
    public void start(){

    }
    //** chars ->  ALPHANUM oper *//*
    public void chars(){


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