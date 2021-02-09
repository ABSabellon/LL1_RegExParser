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
        start(); //state 0
    }

    /**
     * start -> chars | LP start RP oper | EPSILON
     */
    public void start(){ //state 0
        if(super.parseEval){
            Token lookaheadToken = lookahead(super.index); //get next token
            Token current = tokens.get(super.index); //get current token
            final boolean isAFinalState = true; //start is a final state
            handleParen(current);

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
                else if(current.getType() == TokenType.EPSILON && lookaheadToken != null){
                    reject();
                }
            }
            //if last token
            if(lookaheadToken == null){
                if(!isAFinalState || !isParenBalance()) {
                    reject();
                }
            }
            else { //has next token
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
                    start();
                }
                else if(lookaheadToken.getType() == TokenType.RIGHT_PAR){
                    super.index++;
                    start();
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
            handleParen(current);

            //if last token
            if(lookaheadToken == null){
                if(!isAFinalState || !isParenBalance()) {
                    reject();
                }
            }
            else { //has next token
                if (lookaheadToken.getType() == TokenType.ALPHANUM) {
                    super.index++;
                    chars();
                }
                else if(lookaheadToken.getType() == TokenType.LEFT_PAR || lookaheadToken.getType() == TokenType.RIGHT_PAR){
                    super.index++;
                    start();
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
        Token lookaheadToken = lookahead(super.index); //get next token
        Token current = tokens.get(super.index); //get current token
        final boolean isAFinalState = true; //start is a final state

        //if last token
        if(lookaheadToken == null){
            if(!isAFinalState || !isParenBalance()) {
                reject();
            }
        }
        else { //has next token
            if(
                lookaheadToken.getType() == TokenType.ALPHANUM ||
                lookaheadToken.getType() == TokenType.LEFT_PAR
            ) {
                super.index++;
                more();
            }
            else if(lookaheadToken.getType() == TokenType.UNION){
                super.index++;
                comb();
            }
            else if(
                lookaheadToken.getType() == TokenType.OPTIONAL ||
                lookaheadToken.getType() == TokenType.ZERO_OR_MANY ||
                lookaheadToken.getType() == TokenType.ONE_OR_MANY
            ){
                reject();
            }
            else if(lookaheadToken.getType() == TokenType.RIGHT_PAR){
                super.index++;
                start();
            }
            else {
                reject();
            }
        }
    }

    /**
     * comb -> UNION factor
     */
    public void comb(){ //state 3
        Token lookaheadToken = lookahead(super.index); //get next token
        Token current = tokens.get(super.index); //get current token
        final boolean isAFinalState = false; //start is a final state

        //if last token
        if(lookaheadToken == null){
            if(!isAFinalState || !isParenBalance()) {
                reject();
            }
        }
        else { //has next token
            if (
                    lookaheadToken.getType() != TokenType.UNION &&
                    lookaheadToken.getType() != TokenType.RIGHT_PAR &&
                    lookaheadToken.getType() != TokenType.ONE_OR_MANY &&
                    lookaheadToken.getType() != TokenType.ZERO_OR_MANY &&
                    lookaheadToken.getType() != TokenType.OPTIONAL
            ) {
                super.index++;
                factor();
            }
            else {
                reject();
            }
        }
    }

    /**
     * factor -> start | EPSILON
     */
    public void factor(){ //state 4
        Token lookaheadToken = lookahead(super.index); //get next token
        Token current = tokens.get(super.index); //get current token
        final boolean isAFinalState = true; //start is a final state
        handleParen(current);

        //if last token
        if(lookaheadToken == null){
            if(!isAFinalState || !isParenBalance()) {
                reject();
            }
        }
        else { //has next token
            if(current.getType() == TokenType.EPSILON){
                reject();
            }
            else {
                super.index++;
                start();
            }
        }
    }

    /**
     * more-> start | comb | ε
     */
    public void more(){ //state 5
        Token lookaheadToken = lookahead(super.index); //get next token
        Token current = tokens.get(super.index); //get current token
        final boolean isAFinalState = false; //start is a final state
        handleParen(current);

        //if last token
        if(lookaheadToken == null){
            if(!isAFinalState || !isParenBalance()) {
                reject(); //Print Error
            }
        }
        else { //has next token
            if(lookaheadToken.getType() == TokenType.UNION){
                super.index++;
                comb();
            }
            else {
                super.index++;
                start();
            }
        }
    }
}