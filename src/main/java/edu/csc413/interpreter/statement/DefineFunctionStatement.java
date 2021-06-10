package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;

import java.util.List;

public class DefineFunctionStatement implements Statement {
    private String tempFuncName;
    private List<String> tempParaName;
    private List<Statement> tempFuncStatement;

    public DefineFunctionStatement(String functionName, List<String> parameterNames, List<Statement> functionStatements) {
        this.tempFuncName = functionName;
        this.tempParaName = parameterNames;
        this.tempFuncStatement = functionStatements;

    }

    @Override
    public void run(ProgramState programState) {
        programState.registerFunction(this.tempFuncName, this.tempParaName,this.tempFuncStatement);
    }
}
