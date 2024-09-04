package com.example.fainalprojectcompiler;

import java.util.*;

public class Parser {
    private Scanner scanner;
    private GrammarTokens activeToken;
    private Map<String, Map<String, String>> parsingTable;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
        this.activeToken = scanner.getNextToken();
        Builder builder = new Builder();
        this.parsingTable=builder.buildParsingTable();
//        builder.printParsingTable(parsingTable);
    }
    public void verify(GrammarTokens.ResearvedTokens type) throws SyntaxErrorException  {
        if (activeToken.token == type) {
            activeToken = scanner.getNextToken();
        } else {
            throw new SyntaxErrorException ("ERROR : this is unvalid token ==>"+activeToken);
        }
    }
    public void parse() throws Exception {
        verify(GrammarTokens.ResearvedTokens.MODULE);
        verify(GrammarTokens.ResearvedTokens.NAMES);
        verify(GrammarTokens.ResearvedTokens.SEMICOLON);
        declarations();
        block();
        verify(GrammarTokens.ResearvedTokens.NAMES);
        verify(GrammarTokens.ResearvedTokens.DOT);
    }
    private void declarations() throws Exception {

        if (activeToken.token == GrammarTokens.ResearvedTokens.CONST) {
            constDecl();
        }
        if (activeToken.token == GrammarTokens.ResearvedTokens.VAR) {
            varDecl();
        }
        if (activeToken.token == GrammarTokens.ResearvedTokens.PROCEDURE) {
            procedureDecl();
        }
    }

    private void constDecl() throws Exception {
        verify(GrammarTokens.ResearvedTokens.CONST);
        while (activeToken.token == GrammarTokens.ResearvedTokens.NAMES) {
            verify(GrammarTokens.ResearvedTokens.NAMES);
            verify(GrammarTokens.ResearvedTokens.EQUAL);
            if (activeToken.token == GrammarTokens.ResearvedTokens.INTEGER_VALUE || activeToken.token == GrammarTokens.ResearvedTokens.REAL_VALUE) {
                activeToken = scanner.getNextToken();
            } else {
                throw new Exception("Syntax error");
            }
            verify(GrammarTokens.ResearvedTokens.SEMICOLON);
        }
    }

    private void varDecl() throws Exception {
        verify(GrammarTokens.ResearvedTokens.VAR);
        while (activeToken.token == GrammarTokens.ResearvedTokens.NAMES) {
            do {
                verify(GrammarTokens.ResearvedTokens.NAMES);
                if (activeToken.token == GrammarTokens.ResearvedTokens.COMMA) {
                    verify(GrammarTokens.ResearvedTokens.COMMA);
                } else {
                    break;
                }
            } while (activeToken.token == GrammarTokens.ResearvedTokens.NAMES);

            verify(GrammarTokens.ResearvedTokens.COLON);
            if (activeToken.token == GrammarTokens.ResearvedTokens.INTEGER ||
                    activeToken.token == GrammarTokens.ResearvedTokens.REAL ||
                    activeToken.token == GrammarTokens.ResearvedTokens.CHAR) {
                activeToken = scanner.getNextToken();
            } else {
                throw new Exception("Syntax error");
            }
            verify(GrammarTokens.ResearvedTokens.SEMICOLON);
        }
    }

    private void procedureDecl() throws Exception {
        while (activeToken.token == GrammarTokens.ResearvedTokens.PROCEDURE) {
            verify(GrammarTokens.ResearvedTokens.PROCEDURE);
            verify(GrammarTokens.ResearvedTokens.NAMES);
            verify(GrammarTokens.ResearvedTokens.SEMICOLON);
            declarations();
            block();
            verify(GrammarTokens.ResearvedTokens.NAMES);
            verify(GrammarTokens.ResearvedTokens.SEMICOLON);
        }
    }

    private void block() throws Exception {
        verify(GrammarTokens.ResearvedTokens.BEGIN);
        stmtList();
        verify(GrammarTokens.ResearvedTokens.END);
    }

    private void stmtList() throws Exception {
        while (activeToken.token == GrammarTokens.ResearvedTokens.NAMES || activeToken.token == GrammarTokens.ResearvedTokens.IF ||
                activeToken.token == GrammarTokens.ResearvedTokens.WHILE || activeToken.token == GrammarTokens.ResearvedTokens.LOOP ||
                activeToken.token == GrammarTokens.ResearvedTokens.EXIT || activeToken.token == GrammarTokens.ResearvedTokens.CALL ||
                activeToken.token == GrammarTokens.ResearvedTokens.BEGIN || activeToken.token == GrammarTokens.ResearvedTokens.READINT ||
                activeToken.token == GrammarTokens.ResearvedTokens.READREAL || activeToken.token == GrammarTokens.ResearvedTokens.READCHAR ||
                activeToken.token == GrammarTokens.ResearvedTokens.READLN || activeToken.token == GrammarTokens.ResearvedTokens.WRITEINT ||
                activeToken.token == GrammarTokens.ResearvedTokens.WRITEREAL || activeToken.token == GrammarTokens.ResearvedTokens.WRITECHAR ||
                activeToken.token == GrammarTokens.ResearvedTokens.WRITELN) {
            statement();
            verify(GrammarTokens.ResearvedTokens.SEMICOLON);
        }
    }

    private void statement() throws Exception {
        if (activeToken.token == GrammarTokens.ResearvedTokens.NAMES) {
            verify(GrammarTokens.ResearvedTokens.NAMES);
            verify(GrammarTokens.ResearvedTokens.ASSIGN);
            exp();
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.READINT ||
                activeToken.token == GrammarTokens.ResearvedTokens.READREAL ||
                activeToken.token == GrammarTokens.ResearvedTokens.READCHAR ||
                activeToken.token == GrammarTokens.ResearvedTokens.READLN) {
            readStmtment();
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.WRITEINT ||
                activeToken.token == GrammarTokens.ResearvedTokens.WRITEREAL ||
                activeToken.token == GrammarTokens.ResearvedTokens.WRITECHAR ||
                activeToken.token == GrammarTokens.ResearvedTokens.WRITELN) {
            writeStatment();
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.IF) {
            ifStatment();
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.WHILE) {
            whileStatment();
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.LOOP) {
            loopStatement();
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.EXIT) {
            verify(GrammarTokens.ResearvedTokens.EXIT);
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.CALL) {
            verify(GrammarTokens.ResearvedTokens.CALL);
            verify(GrammarTokens.ResearvedTokens.NAMES);
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.BEGIN) {
            block();
        } else {
            throw new Exception("syntax error");
        }
    }

    private void exp() throws Exception {
        term();
        while (activeToken.token == GrammarTokens.ResearvedTokens.ADD || activeToken.token == GrammarTokens.ResearvedTokens.SUB) {
            activeToken = scanner.getNextToken();
            term();
        }
    }

    private void term() throws Exception {
        factor();
        while (activeToken.token == GrammarTokens.ResearvedTokens.MUL || activeToken.token == GrammarTokens.ResearvedTokens.DIV ||
                activeToken.token == GrammarTokens.ResearvedTokens.MOD) {
            activeToken = scanner.getNextToken();
            factor();
        }
    }

    private void factor() throws Exception {
        if (activeToken.token == GrammarTokens.ResearvedTokens.LPAREN) {
            verify(GrammarTokens.ResearvedTokens.LPAREN);
            exp();
            verify(GrammarTokens.ResearvedTokens.RPAREN);
        } else if (activeToken.token == GrammarTokens.ResearvedTokens.NAMES || activeToken.token == GrammarTokens.ResearvedTokens.INTEGER_VALUE ||
                activeToken.token == GrammarTokens.ResearvedTokens.REAL_VALUE) {
            activeToken = scanner.getNextToken();
        } else {
            throw new Exception("Syntax Error: Invalid factor");
        }
    }

    private void readStmtment() throws SyntaxErrorException {
        activeToken = scanner.getNextToken();
        if (activeToken.token == GrammarTokens.ResearvedTokens.LPAREN) {
            verify(GrammarTokens.ResearvedTokens.LPAREN);
            verify(GrammarTokens.ResearvedTokens.NAMES);
            while (activeToken.token == GrammarTokens.ResearvedTokens.COMMA) {
                verify(GrammarTokens.ResearvedTokens.COMMA);
                verify(GrammarTokens.ResearvedTokens.NAMES);
            }
            verify(GrammarTokens.ResearvedTokens.RPAREN);
        }
    }

    private void writeStatment() throws Exception {
        activeToken = scanner.getNextToken();
        if (activeToken.token == GrammarTokens.ResearvedTokens.LPAREN) {
            verify(GrammarTokens.ResearvedTokens.LPAREN);
            writeItem();
            while (activeToken.token == GrammarTokens.ResearvedTokens.COMMA) {
                verify(GrammarTokens.ResearvedTokens.COMMA);
                writeItem();
            }
            verify(GrammarTokens.ResearvedTokens.RPAREN);
        }
    }

    private void writeItem() throws SyntaxErrorException {
        if (activeToken.token == GrammarTokens.ResearvedTokens.NAMES || activeToken.token == GrammarTokens.ResearvedTokens.INTEGER_VALUE ||
                activeToken.token == GrammarTokens.ResearvedTokens.REAL_VALUE) {
            activeToken = scanner.getNextToken();
        } else {
            throw new SyntaxErrorException("Syntax error");
        }
    }

    private void ifStatment() throws Exception {
        verify(GrammarTokens.ResearvedTokens.IF);
        conditions();
        verify(GrammarTokens.ResearvedTokens.THEN);
        stmtList();
        if (activeToken.token == GrammarTokens.ResearvedTokens.ELSE) {
            verify(GrammarTokens.ResearvedTokens.ELSE);
            stmtList();
        }
        verify(GrammarTokens.ResearvedTokens.END);
    }

    private void whileStatment() throws Exception {
        verify(GrammarTokens.ResearvedTokens.WHILE);
        conditions();
        verify(GrammarTokens.ResearvedTokens.DO);
        stmtList();
        verify(GrammarTokens.ResearvedTokens.END);
    }

    private void loopStatement() throws Exception {
        verify(GrammarTokens.ResearvedTokens.LOOP);
        stmtList();
        verify(GrammarTokens.ResearvedTokens.UNTIL);
        conditions();
    }

    private void conditions() throws SyntaxErrorException {
        if (activeToken.token == GrammarTokens.ResearvedTokens.NAMES || activeToken.token == GrammarTokens.ResearvedTokens.INTEGER_VALUE ||
                activeToken.token == GrammarTokens.ResearvedTokens.REAL_VALUE) {
            activeToken = scanner.getNextToken();
        } else {
            throw new SyntaxErrorException("Syntax error");
        }
        if (activeToken.token == GrammarTokens.ResearvedTokens.EQUAL || activeToken.token == GrammarTokens.ResearvedTokens.NOT_EQUAL ||
                activeToken.token == GrammarTokens.ResearvedTokens.LESS || activeToken.token == GrammarTokens.ResearvedTokens.LESS_EQUAL ||
                activeToken.token == GrammarTokens.ResearvedTokens.GREATER || activeToken.token == GrammarTokens.ResearvedTokens.GREATER_EQUAL) {
            activeToken = scanner.getNextToken();
            if (activeToken.token == GrammarTokens.ResearvedTokens.NAMES || activeToken.token == GrammarTokens.ResearvedTokens.INTEGER_VALUE ||
                    activeToken.token == GrammarTokens.ResearvedTokens.REAL_VALUE) {
                activeToken = scanner.getNextToken();
            } else {
                throw new SyntaxErrorException("Syntax error");
            }
        } else {
            throw new SyntaxErrorException("Syntax error");
        }
    }
}

