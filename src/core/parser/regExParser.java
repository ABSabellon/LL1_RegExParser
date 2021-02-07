package core.parser;

import core.lexer.Token;
import core.lexer.TokenType;

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
    public regExParser(String scannedTokens) {
        super(scannedTokens);
        //start(0); //state 0
    }

    private void reject(){
        super.parseEvalString = "REJECTED";
        super.parseEval = false;
    }

    //** start -> chars | LP start RP oper | EPSILON *//
    public void start(int index){ //state 0
//        Token lookaheadToken = lookahead(index);
//        Token current = tokens.get(index);
//
//        if(lookaheadToken != null) {
//            if(current.getType() == TokenType.REJECT || current.getType() == TokenType.RIGHT_PAR || current.getType() == TokenType.UNION){
//                super.parseEvalString = "REJECTED";
//                super.parseEval = false;
//            }
//            else if(current.getType() == TokenType.LEFT_PAR){ //open paren
//                super.openParen();
//            }
//            else if(current.getType() != TokenType.EPSILON){ //E
//                if(lookaheadToken.getType() == TokenType.ALPHANUM){
//                    chars(index++);
//                }
//                else if(
//                    lookaheadToken.getType() == TokenType.OPTIONAL ||
//                    lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
//                    lookaheadToken.getType() == TokenType.ONE_OR_MANY
//                ) {
//                    chars(index++);
//                }
//                else if(lookaheadToken.getType() == TokenType.UNION){
//                    chars(index++);
//                }
//                else if(lookaheadToken.getType() == TokenType.LEFT_PAR){
//                    start(index++);
//                }
//                else if(lookaheadToken.getType() == TokenType.RIGHT_PAR){
//                    oper(index++);
//                }
//                else if(lookaheadToken == null){ //no more next tokens
//                    if(!isParenBalance()){
//                        reject();
//                    }
//                }
//            }
//        }
    }

    //** chars ->  ALPHANUM oper *//*
    public void chars(int index){ //state 1
//        Token lookaheadToken = lookahead(index);
//        // Token current = tokens.get(index); guaranteed alphanum
//
//        if(lookaheadToken != null) {
//            if (lookaheadToken.getType() == TokenType.ALPHANUM) {
//                oper(index++);
//            } else if (lookaheadToken == null) { //no more next tokens
//                if (!isParenBalance()) {
//                    reject();
//                }
//            } else {
//                reject();
//            }
//        }
    }

    //** oper -> OPERATIONS more | comb | ε *//*
    public void oper(int index){ //state 2
//        Token lookaheadToken = lookahead(index);
//        if(lookaheadToken != null) {
//            if(
//                lookaheadToken.getType() == TokenType.OPTIONAL ||
//                lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
//                lookaheadToken.getType() == TokenType.ONE_OR_MANY ||
//                lookaheadToken.getType() == TokenType.ALPHANUM
//            ) {
//                more(index++);
//            }
//            else if(lookaheadToken.getType() == TokenType.UNION){
//                comb(index++);
//            }
//            else if(lookaheadToken == null){ //no more next tokens
//                if(!isParenBalance()){
//                    reject();
//                }
//            }
//            else {
//                reject();
//            }
//        }
    }

    //** comb -> UNION factor *//*
    public void comb(int index){ //state 3
//        Token lookaheadToken = lookahead(index);
//        if(lookaheadToken != null) {
//            if (lookaheadToken.getType() == TokenType.UNION) {
//                factor(index++);
//            } else if (lookaheadToken == null) { //no more next tokens
//                if (!isParenBalance()) {
//                    reject();
//                }
//            } else {
//                reject();
//            }
//        }
    }

    //** factor -> start | EPSILON *//*
    public void factor(int index){ //state 4
//        Token lookaheadToken = lookahead(index);
//        if(lookaheadToken != null) {
//            if (lookaheadToken == null) { //no more next tokens
//                if (!isParenBalance()) {
//                    reject();
//                }
//            } else {
//                start(index++);
//            }
//        }
    }

    //** more-> start | comb | ε *//*
    public void more(int index){ //state 5
//        Token lookaheadToken = lookahead(index);
//        if(lookaheadToken != null) {
//            if(lookaheadToken.getType() == TokenType.UNION){
//                comb(index++);
//            }
//            else if(lookaheadToken == null){ //no more next tokens
//                if(!isParenBalance()){
//                    reject();
//                }
//            }
//            else {
//                start(index++);
//            }
//        }
    }
}