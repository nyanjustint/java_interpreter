package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

public class ReturnStatement implements Statement {

    private Expression tempExp;
    public ReturnStatement(Expression temp) {
        this.tempExp = temp;
    }

    @Override
    public void run(ProgramState programState) {
        programState.setReturnValue(tempExp.evaluate(programState));
    }
}
