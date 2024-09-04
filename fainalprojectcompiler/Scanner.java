package com.example.fainalprojectcompiler;

public class Scanner {
    private String code;
    private int position;
    private char activeChar;

    public Scanner(String code) {
        this.code = code;
        this.position = 0;
        this.activeChar = code.length() > 0 ? code.charAt(position) : '\0';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public char getActiveChar() {
        return activeChar;
    }

    public void setActiveChar(char activeChar) {
        this.activeChar = activeChar;
    }

    private void getNextChar() {
        position++;
        if (position < code.length()) {
            activeChar = code.charAt(position);
        } else {
            activeChar = '\0';
        }
    }

    private GrammarTokens identifiers() {
        StringBuilder word = new StringBuilder();
        while (Character.isLetter(activeChar) || Character.isDigit(activeChar)) {
            word.append(activeChar);
            getNextChar();
        }
        return getToken(word.toString());
    }

    private GrammarTokens getToken(String value) {
        switch (value) {
            case "module":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.MODULE, value);
            case "begin":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.BEGIN, value);
            case "end":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.END, value);
            case "const":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.CONST, value);
            case "div":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.DIV, value);
            case "exit":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.EXIT, value);
            case "call":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.CALL, value);
            case "readint":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.READINT, value);
            case "readreal":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.READREAL, value);
            case "readchar":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.READCHAR, value);
            case "readln":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.READLN, value);
            case "writeint":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.WRITEINT, value);
            case "writereal":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.WRITEREAL, value);
            case "writechar":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.WRITECHAR, value);
            case "writeln":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.WRITELN, value);
            case "integer":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.INTEGER, value);
            case "var":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.VAR, value);
            case "procedure":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.PROCEDURE, value);
            case "if":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.IF, value);
            case "then":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.THEN, value);
            case "else":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.ELSE, value);
            case "while":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.WHILE, value);
            case "do":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.DO, value);
            case "loop":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.LOOP, value);
            case "until":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.UNTIL, value);
            case "mod":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.MOD, value);
            case "real":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.REAL, value);
            case "char":
                return new GrammarTokens(GrammarTokens.ResearvedTokens.CHAR, value);
            default:
                return new GrammarTokens(GrammarTokens.ResearvedTokens.NAMES, value);
        }
    }

    private GrammarTokens numbers() {
        StringBuilder num = new StringBuilder();
        boolean isFloat = false;
        while (Character.isDigit(activeChar)) {
            num.append(activeChar);
            getNextChar();
        }
        if (activeChar == '.') {
            num.append(activeChar);
            getNextChar();
            isFloat = true;
            while (Character.isDigit(activeChar)) {
                num.append(activeChar);
                getNextChar();
            }
        }
        if (isFloat)
            return new GrammarTokens(GrammarTokens.ResearvedTokens.REAL_VALUE, num.toString());
        else
            return new GrammarTokens(GrammarTokens.ResearvedTokens.INTEGER_VALUE, num.toString());
    }

    public GrammarTokens getNextToken() {
        while (activeChar != '\0') {
            if (Character.isDigit(activeChar)) {
                return numbers();
            }
            if (Character.isLetter(activeChar)) {
                return identifiers();
            }
            if (Character.isWhitespace(activeChar)) {
                while (Character.isWhitespace(activeChar)) {
                    getNextChar();
                }
                continue;
            }
            switch (activeChar) {
                case ':':
                    getNextChar();
                    if (activeChar == '=') {
                        getNextChar();
                        return new GrammarTokens(GrammarTokens.ResearvedTokens.ASSIGN, ":=");
                    }
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.COLON, ":");
                case '+':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.ADD, "+");
                case '-':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.SUB, "-");
                case '*':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.MUL, "*");
                case '/':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.DIV, "/");
                case '(':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.LPAREN, "(");
                case ')':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.RPAREN, ")");
                case ';':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.SEMICOLON, ";");
                case '=':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.EQUAL, "=");
                case '<':
                    getNextChar();
                    if (activeChar == '=') {
                        getNextChar();
                        return new GrammarTokens(GrammarTokens.ResearvedTokens.LESS_EQUAL, "<=");
                    }
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.LESS, "<");
                case '>':
                    getNextChar();
                    if (activeChar == '=') {
                        getNextChar();
                        return new GrammarTokens(GrammarTokens.ResearvedTokens.GREATER_EQUAL, ">=");
                    }
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.GREATER, ">");
                case '.':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.DOT, ".");
                case ',':
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.COMMA, ",");
                default:
                    getNextChar();
                    return new GrammarTokens(GrammarTokens.ResearvedTokens.ERROR, String.valueOf(activeChar));
            }
        }
        return new GrammarTokens(GrammarTokens.ResearvedTokens.EOF, "");
    }
}
