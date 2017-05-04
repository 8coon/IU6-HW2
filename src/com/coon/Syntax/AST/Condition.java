package com.coon.Syntax.AST;


import com.coon.Lexical.Token;


public class Condition extends ASTItem {

    private Operand condition;


    public Condition(Token condition) {
        this.condition = new Operand(condition);
        this.getChildren().clear();

        this.getChildren().add(new IfBranch(true));
        this.getChildren().add(new IfBranch(false));
    }


    @Override
    public String toStringInfo() {
        return condition.toStringInfo() + "?";
    }


    public void append(boolean truthy, ASTItem item) {
        int index = 0;

        if (!truthy) {
            index = 1;
        }

        this.getChildren().get(index).getChildren().add(item);
    }

}
