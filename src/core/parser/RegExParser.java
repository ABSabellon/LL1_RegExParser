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

/**
 * Parser
 * */
public class RegExParser extends Parser {
    public RegExParser(List<Token> scannedTokens) {
        super(scannedTokens);
        super.index = 0;
        regEx(); //state 0
    }

    /**
     * start -> chars | LP start RP oper | EPSILON
     */
    public void regEx(){ //state 0
        if(super.parseEval){
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = true; //start is a final state

            //if first ever token
            if(startOfToken(super.index) == null){
                //validate first token
                if(
                    current.getType() == TokenType.REJECT ||
                    current.getType() == TokenType.RIGHT_PAR ||
                    current.getType() == TokenType.UNION ||
                    current.getType() == TokenType.OPTIONAL ||
                    current.getType() == TokenType.ZERO_OR_MANY ||
                    current.getType() == TokenType.ONE_OR_MANY
                ){
                    reject();
                }
                else if(
                    current.getType() == TokenType.EPSILON &&
                    lookaheadToken != null &&
                    lookaheadToken.getType() != TokenType.UNION
                ){
                    reject();
                }
            }
            //if last token
            if(lookaheadToken != null){
                if(
                    current.getType() == TokenType.LEFT_PAR &&
                    (
                        lookaheadToken.getType() == TokenType.UNION ||
                        lookaheadToken.getType() == TokenType.OPTIONAL ||
                        lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
                        lookaheadToken.getType() == TokenType.ONE_OR_MANY
                    )
                ){
                    reject();
                }
                if(lookaheadToken.getType() == TokenType.ALPHANUM){
                    super.index++;
                    chars();
                }
                else if(
                    lookaheadToken.getType() == TokenType.OPTIONAL ||
                    lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
                    lookaheadToken.getType() == TokenType.ONE_OR_MANY
                ){
                    super.index++;
                    oper();
                }
                else if(lookaheadToken.getType() == TokenType.UNION){
                    super.index++;
                    chars();
                }
                else if(lookaheadToken.getType() == TokenType.LEFT_PAR){
                    super.index++;
                    regEx();
                }
                else if(lookaheadToken.getType() == TokenType.RIGHT_PAR){
                    super.index++;
                    regEx();
                }
                else if(lookaheadToken.getType() == TokenType.EPSILON){
                    super.index++;
                    epsilon();
                }
            }
        }
    }

    /**
     * chars ->  ALPHANUM oper
     */
    public void chars(){ //state 1
        if(super.parseEval) {
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = true; //start is a final state

            //if last token
            if(lookaheadToken != null){
                if (lookaheadToken.getType() == TokenType.ALPHANUM) {
                    super.index++;
                    chars();
                }
                else if(lookaheadToken.getType() == TokenType.LEFT_PAR || lookaheadToken.getType() == TokenType.RIGHT_PAR){
                    super.index++;
                    regEx();
                }
                else if(
                    lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
                    lookaheadToken.getType() == TokenType.ONE_OR_MANY ||
                    lookaheadToken.getType() == TokenType.OPTIONAL
                ){
                    super.index++;
                    oper();
                }
                else if(lookaheadToken.getType() == TokenType.UNION){
                    super.index++;
                    comb();
                }
                else if(lookaheadToken.getType() == TokenType.EPSILON){
                    super.index++;
                    epsilon();
                }
                else {
                    reject();
                }
            }
        }
    }

    /**
     * oper -> OPERATIONS more | comb | ε
     */
    public void oper(){ //state 2
        if(super.parseEval) {
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = true; //start is a final state

            //if last token
            if (lookaheadToken != null) {
                if (
                    lookaheadToken.getType() == TokenType.ALPHANUM ||
                    lookaheadToken.getType() == TokenType.LEFT_PAR
                ) {
                    super.index++;
                    more();
                } else if (lookaheadToken.getType() == TokenType.UNION) {
                    super.index++;
                    comb();
                } else if (lookaheadToken.getType() == TokenType.RIGHT_PAR) {
                    super.index++;
                    regEx();
                } else if (
                    lookaheadToken.getType() == TokenType.OPTIONAL ||
                    lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
                    lookaheadToken.getType() == TokenType.ONE_OR_MANY
                ) {
                    reject();
                } else {
                    reject();
                }
            }
        }
    }

    /**
     * comb -> UNION factor
     */
    public void comb(){ //state 3
        if(super.parseEval) {
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = false; //start is a final state

            //if last token
            if (lookaheadToken == null) {
                reject();
            } else { //has next token
                if (
                    lookaheadToken.getType() != TokenType.UNION &&
                    lookaheadToken.getType() != TokenType.RIGHT_PAR &&
                    lookaheadToken.getType() != TokenType.ONE_OR_MANY &&
                    lookaheadToken.getType() != TokenType.ZERO_OR_MANY &&
                    lookaheadToken.getType() != TokenType.OPTIONAL
                ) {
                    super.index++;
                    regEx();
                }
                else if(lookaheadToken.getType() == TokenType.EPSILON){
                    super.index++;
                    epsilon();
                }
                else {
                    reject();
                }
            }
        }
    }

    public void epsilon(){
        if(super.parseEval) {
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = true; //start is a final state

            //if last token
            if (lookaheadToken != null) {
                if (lookaheadToken.getType() == TokenType.UNION) {
                    super.index++;
                    comb();
                } else if (lookaheadToken.getType() == TokenType.RIGHT_PAR) {
                    super.index++;
                    regEx();
                }
                else if (
                    lookaheadToken.getType() == TokenType.ONE_OR_MANY ||
                    lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
                    lookaheadToken.getType() == TokenType.OPTIONAL ||
                    lookaheadToken.getType() == TokenType.ALPHANUM
                ) {
                    reject();
                } else {
                    reject();
                }
            }
        }
    }

    /**
     * more-> start | comb | ε
     */
    public void more(){ //state 5
        if(super.parseEval) {
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = true; //start is a final state

            //if last token
            if (lookaheadToken != null) {
                if (lookaheadToken.getType() == TokenType.UNION) {
                    super.index++;
                    comb();
                } else if (lookaheadToken.getType() == TokenType.EPSILON) {
                    reject();
                } else {
                    super.index++;
                    regEx();
                }
            }
        }
    }
}