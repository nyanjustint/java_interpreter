package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

import java.util.List;

public class ForStatement extends BlockStatement {

    private String varName;
    private Expression start;
    private Expression end;

    public ForStatement(String loopVarName, Expression exp1, Expression exp2, List<Statement> body) {
        super(null, body);
        this.start = exp1;
        this.end = exp2;
        this.varName = loopVarName;
    }

    @Override
    public void run(ProgramState programState) {

        int tempStart, tempEnd;
        tempStart = this.start.evaluate(programState);
        tempEnd = this.end.evaluate(programState);
        programState.setVariable(this.varName, tempStart);

        while(programState.getVariable(this.varName) != tempEnd) {
            for(Statement sup:super.getStatement()){
                sup.run(programState);
            }
            programState.setVariable(this.varName, programState.getVariable(this.varName) + 1);
        }
    }
}
