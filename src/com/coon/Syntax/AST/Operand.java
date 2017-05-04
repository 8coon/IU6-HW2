package com.coon.Syntax.AST;


import com.coon.Lexical.Token;

public class Operand extends ASTItem {

    private Token token;


    public Operand(Token token) {
        this.token = token;
    }


    @Override
    public String toStringInfo() {
        StringBuilder sb = new StringBuilder();

        switch (this.token.getType()) {
            case IDENTIFIER: {
                sb.append("(идентификатор) ");
            } break;

            case CONST: {
                sb.append("(константа) ");
            } break;

            case HEX: {
                sb.append("(шестнадцатеричная константа) ");
            } break;
        }

        sb.append(this.token.getRead());
        return sb.toString();
    }


    public Token getToken() {
        return this.token;
    }

}
