package com.example.fainalprojectcompiler;
import java.util.HashMap;
import java.util.Map;

public class Builder {

    public Map<String, Map<String, String>> buildParsingTable() {
        Map<String, Map<String, String>> parsingTable = new HashMap<>();

        Map<String, String> prog = new HashMap<>();
        prog.put("MODULE", "module identifier ; declarations block identifier .");
        parsingTable.put("program", prog);

        Map<String, String> declarations = new HashMap<>();
        declarations.put("CONST", "const-decl var-decl procedure-decl");
        declarations.put("VAR", "var-decl procedure-decl");
        declarations.put("PROCEDURE", "procedure-decl");
        declarations.put("BEGIN", "");
        parsingTable.put("declarations", declarations);

        Map<String, String> constant = new HashMap<>();
        constant.put("CONST", "const const-list");
        constant.put("VAR", "");
        constant.put("PROCEDURE", "");
        constant.put("BEGIN", "");
        parsingTable.put("const-decl", constant);

        Map<String, String> constantList = new HashMap<>();
        constantList.put("NAMES", "identifier = constant ; const-list");
        parsingTable.put("const-list", constantList);

        Map<String, String> variable = new HashMap<>();
        variable.put("VAR", "var var-list");
        variable.put("PROCEDURE", "");
        variable.put("BEGIN", "");
        parsingTable.put("var-decl", variable);

        Map<String, String> variableList = new HashMap<>();
        variableList.put("NAMES", "identifier : type ; var-list");
        parsingTable.put("var-list", variableList);

        Map<String, String> dataType = new HashMap<>();
        dataType.put("INTEGER", "integer");
        dataType.put("REAL", "real");
        dataType.put("CHAR", "char");
        parsingTable.put("type", dataType);

        Map<String, String> procedure = new HashMap<>();
        procedure.put("PROCEDURE", "procedure identifier ; declarations block identifier ; procedure-decl");
        procedure.put("BEGIN", "");
        parsingTable.put("procedure-decl", procedure);

        Map<String, String> block = new HashMap<>();
        block.put("BEGIN", "begin stmt-list end");
        parsingTable.put("block", block);

        Map<String, String> statmentList = new HashMap<>();
        statmentList.put("NAMES", "statement ; stmt-list");
        statmentList.put("IF", "statement ; stmt-list");
        statmentList.put("WHILE", "statement ; stmt-list");
        statmentList.put("LOOP", "statement ; stmt-list");
        statmentList.put("EXIT", "statement ; stmt-list");
        statmentList.put("CALL", "statement ; stmt-list");
        statmentList.put("BEGIN", "statement ; stmt-list");
        statmentList.put("READINT", "statement ; stmt-list");
        statmentList.put("READREAL", "statement ; stmt-list");
        statmentList.put("READCHAR", "statement ; stmt-list");
        statmentList.put("READLN", "statement ; stmt-list");
        statmentList.put("WRITEINT", "statement ; stmt-list");
        statmentList.put("WRITEREAL", "statement ; stmt-list");
        statmentList.put("WRITECHAR", "statement ; stmt-list");
        statmentList.put("WRITELN", "statement ; stmt-list");
        statmentList.put("END", "");
        parsingTable.put("stmt-list", statmentList);

        Map<String, String> statement = new HashMap<>();
        statement.put("NAMES", "identifier := exp");
        statement.put("READINT", "readStmt");
        statement.put("READREAL", "readStmt");
        statement.put("READCHAR", "readStmt");
        statement.put("READLN", "readStmt");
        statement.put("WRITEINT", "writeStmt");
        statement.put("WRITEREAL", "writeStmt");
        statement.put("WRITECHAR", "writeStmt");
        statement.put("WRITELN", "writeStmt");
        statement.put("IF", "ifStmt");
        statement.put("WHILE", "whileStmt");
        statement.put("LOOP", "loopStmt");
        statement.put("EXIT", "exit");
        statement.put("CALL", "call identifier");
        statement.put("BEGIN", "block");
        parsingTable.put("statement", statement);

        Map<String, String> exp = new HashMap<>();
        exp.put("NAMES", "term exp'");
        exp.put("INTEGER_VALUE", "term exp'");
        exp.put("REAL_VALUE", "term exp'");
        exp.put("LPAREN", "term exp'");
        parsingTable.put("exp", exp);

        Map<String, String> expPrime = new HashMap<>();
        expPrime.put("ADD", "+ term exp'");
        expPrime.put("SUB", "- term exp'");
        expPrime.put("SEMICOLON", "");
        expPrime.put("RPAREN", "");
        parsingTable.put("exp'", expPrime);

        Map<String, String> terms = new HashMap<>();
        terms.put("NAMES", "factor term'");
        terms.put("INTEGER_VALUE", "factor term'");
        terms.put("REAL_VALUE", "factor term'");
        terms.put("LPAREN", "factor term'");
        parsingTable.put("term", terms);

        Map<String, String> termsPrime = new HashMap<>();
        termsPrime.put("MUL", "* factor term'");
        termsPrime.put("DIV", "/ factor term'");
        termsPrime.put("MOD", "mod factor term'");
        termsPrime.put("ADD", "");
        termsPrime.put("SUB", "");
        termsPrime.put("SEMICOLON", "");
        termsPrime.put("RPAREN", "");
        parsingTable.put("term'", termsPrime);

        Map<String, String> factors = new HashMap<>();
        factors.put("NAMES", "identifier");
        factors.put("INTEGER_VALUE", "integer_value");
        factors.put("REAL_VALUE", "real_value");
        factors.put("LPAREN", "( exp )");
        parsingTable.put("factor", factors);

        Map<String, String> readStatment = new HashMap<>();
        readStatment.put("READINT", "readint ( idList )");
        readStatment.put("READREAL", "readreal ( idList )");
        readStatment.put("READCHAR", "readchar ( idList )");
        readStatment.put("READLN", "readln ( idList )");
        parsingTable.put("readStmt", readStatment);

        Map<String, String> writeStmtment = new HashMap<>();
        writeStmtment.put("WRITEINT", "writeint ( writeList )");
        writeStmtment.put("WRITEREAL", "writereal ( writeList )");
        writeStmtment.put("WRITECHAR", "writechar ( writeList )");
        writeStmtment.put("WRITELN", "writeln ( writeList )");
        parsingTable.put("writeStmt", writeStmtment);

        Map<String, String> idListMap = new HashMap<>();
        idListMap.put("NAMES", "identifier , idList");
        parsingTable.put("idList", idListMap);

        Map<String, String> writeListMap = new HashMap<>();
        writeListMap.put("NAMES", "writeItem , writeList");
        writeListMap.put("INTEGER_VALUE", "writeItem , writeList");
        writeListMap.put("REAL_VALUE", "writeItem , writeList");
        parsingTable.put("writeList", writeListMap);

        Map<String, String> writeItemMap = new HashMap<>();
        writeItemMap.put("NAMES", "identifier");
        writeItemMap.put("INTEGER_VALUE", "integer_value");
        writeItemMap.put("REAL_VALUE", "real_value");
        parsingTable.put("writeItem", writeItemMap);

        Map<String, String> ifStmtMap = new HashMap<>();
        ifStmtMap.put("IF", "if condition then stmtList else stmtList end");
        parsingTable.put("ifStmt", ifStmtMap);

        Map<String, String> whileStmtMap = new HashMap<>();
        whileStmtMap.put("WHILE", "while condition do stmtList end");
        parsingTable.put("whileStmt", whileStmtMap);

        Map<String, String> loopStmtMap = new HashMap<>();
        loopStmtMap.put("LOOP", "loop stmtList until condition");
        parsingTable.put("loopStmt", loopStmtMap);

        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("NAMES", "exp relop exp");
        conditionMap.put("INTEGER_VALUE", "exp relop exp");
        conditionMap.put("REAL_VALUE", "exp relop exp");
        parsingTable.put("condition", conditionMap);

        Map<String, String> relopMap = new HashMap<>();
        relopMap.put("EQ", "=");
        relopMap.put("NEQ", "!=");
        relopMap.put("LT", "<");
        relopMap.put("LTE", "<=");
        relopMap.put("GT", ">");
        relopMap.put("GTE", ">=");
        parsingTable.put("relop", relopMap);

        return parsingTable;
    }
//    public void printParsingTable(Map<String, Map<String, String>> parsingTable) {
//        for (Map.Entry<String, Map<String, String>> entry : parsingTable.entrySet()) {
//            String nonTerminal = entry.getKey();
//            Map<String, String> rules = entry.getValue();
//            System.out.println("Non-terminal: " + nonTerminal);
//            for (Map.Entry<String, String> rule : rules.entrySet()) {
//                System.out.println("  Terminal: " + rule.getKey() + " -> " + rule.getValue());
//            }
//        }
//    }

}
