package com.coon.Syntax.AST;


public class IfBranch extends ASTItem {

    private boolean truthy;


    public IfBranch(boolean truthy) {
        this.truthy = truthy;
    }


    @Override
    public String toStringInfo() {
        if (this.truthy) {
            return "Да";
        }

        return "Нет";
    }


    public boolean isTruthy() {
        return this.truthy;
    }

}
