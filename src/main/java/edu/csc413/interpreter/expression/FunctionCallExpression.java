package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallExpression implements Expression {

    private String tempFuncName;
    private List<Expression> tempPara;
    private List<Integer> val;

    public FunctionCallExpression(String functionName, List<Expression> temp) {
        this.tempFuncName = functionName;
        this.tempPara = temp;
        val = new ArrayList<>();
    }

    @Override
    public int evaluate(ProgramState programState) {

        for(Expression e: this.tempPara){
            int tempVal = e.evaluate(programState);
            val.add(tempVal);
        }


        programState.addCallFrame();

        int count=0;
        while( count != this.tempPara.size()){
            programState.setVariable(programState.getParameterNames(this.tempFuncName).get(count), val.get(count));
            count++;
        }

        val.clear();

        List<Statement> tempFuncList = programState.getFunctionStatements(this.tempFuncName);


        for(Statement p: tempFuncList){
            if(!programState.hasReturnValue()){
                p.run(programState);
            }
        }

        int v = programState.getReturnValue();
        programState.clearReturnValue();
        programState.removeCallFrame();
        return v;
    }
}
