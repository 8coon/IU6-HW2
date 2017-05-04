package com.coon.Syntax.AST;


import com.coon.Lexical.Token;


public class Expression extends ASTItem {


    public Expression(Token left, Token right) {
        this.getChildren().clear();

        this.getChildren().add(new Operand(left));
        this.getChildren().add(new Operand(right));
    }


    public String toStringInfo() {
        return "=";
    }

}
