package com.example.fainalprojectcompiler;

public class GrammarTokens {
    public enum ResearvedTokens {
        MODULE, BEGIN, END, CONST, VAR, PROCEDURE, IF, THEN, ELSE, WHILE, DO,
        LOOP, UNTIL, EXIT, CALL, READINT, READREAL, READCHAR, READLN,
        WRITEINT, WRITEREAL, WRITECHAR, WRITELN, INTEGER, REAL, CHAR,
        NAMES, INTEGER_VALUE, REAL_VALUE, ASSIGN, ADD, SUB, MUL, DIV, MOD,
        LPAREN, RPAREN, SEMICOLON, EQUAL, NOT_EQUAL, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL,
        DOT, COLON, COMMA, EOF, ERROR
    }

    public ResearvedTokens token;
    public String tokenName;

    public GrammarTokens(ResearvedTokens type, String lexeme) {
        this.token = type;
        this.tokenName = lexeme;
    }

    public ResearvedTokens getToken() {
        return token;
    }

    public void setToken(ResearvedTokens token) {
        this.token = token;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    @Override
    public String toString() {
        return token + ": " + tokenName;
    }
}
