package com.coon.Syntax;


import com.coon.Lexical.LexicalState;
import com.coon.Lexical.Token;
import com.coon.Syntax.AST.ASTItem;
import com.coon.Syntax.AST.Condition;
import com.coon.Syntax.AST.Expression;

import java.util.ArrayList;
import java.util.List;


public class SyntaxAnalyzer {

    private List<ASTItem> ast = new ArrayList<>();
    private List<Token> tokens;


    private ASTItem scanExpression(int start) throws SyntaxException {
        if (this.tokens.size() <= start + 2) {
            throw new SyntaxException(this.tokens.get(start).getPosition(), "Незаконченное выражение");
        }

        if (!this.tokens.get(start).getType().equals(LexicalState.IDENTIFIER)) {
            throw new SyntaxException(this.tokens.get(start).getPosition(), "Ожидается идентификатор");
        }

        if (!(this.tokens.get(start + 1).getType().equals(LexicalState.SYMBOL) &&
                this.tokens.get(start + 1).getRead().equals("="))) {
            throw new SyntaxException(this.tokens.get(start + 1).getPosition(), "Ожидается =");
        }

        if (!(
                this.tokens.get(start + 2).getType().equals(LexicalState.HEX) ||
                this.tokens.get(start + 2).getType().equals(LexicalState.CONST) ||
                this.tokens.get(start + 2).getType().equals(LexicalState.IDENTIFIER)
        )) {
            throw new SyntaxException(this.tokens.get(start + 2).getPosition(),
                    "Ожидается идентификатор или константа");
        }

        Expression expression = new Expression(this.tokens.get(start), this.tokens.get(start + 2));
        expression.newPosition = start + 3;

        return expression;
    }


    private ASTItem scanIf(int start) throws SyntaxException {
        if (this.tokens.size() <= start + 3) {
            throw new SyntaxException(this.tokens.get(start).getPosition(), "Незаконченное выражение");
        }

        if (!tokens.get(start).getType().equals(LexicalState.IF)) {
            throw new SyntaxException(tokens.get(start).getPosition(), "Ожидается \"if\"");
        }

        if (!(LexicalState.SYMBOL.equals(tokens.get(start + 1).getType()) &&
                "(".equals(tokens.get(start + 1).getRead()))) {
            throw new SyntaxException(tokens.get(start + 1).getPosition(), "Ожидается \"(\"");
        }

        if (!LexicalState.IDENTIFIER.equals(tokens.get(start + 2).getType())) {
            throw new SyntaxException(tokens.get(start + 2).getPosition(), "Ожидается идентификатор");
        }

        if (!(LexicalState.SYMBOL.equals(tokens.get(start + 3).getType()) &&
                ")".equals(tokens.get(start + 3).getRead()))) {
            throw new SyntaxException(tokens.get(start + 3).getPosition(), "Ожидается \")\"");
        }

        Condition condition = new Condition(tokens.get(start + 2));


        if (this.tokens.size() <= start + 4) {
            throw new SyntaxException(this.tokens.get(start).getPosition(), "Незаконченное выражение");
        }

        if (tokens.get(start + 4).getType().equals(LexicalState.IF)) {
            ASTItem cond = this.scanIf(start + 4);

            condition.append(true, cond);
            condition.newPosition = cond.newPosition;

            return condition;
        }

        ASTItem expression = this.scanExpression(start + 4);
        condition.append(true, expression);
        condition.newPosition = expression.newPosition;

        if (tokens.size() == condition.newPosition ||
                !tokens.get(condition.newPosition).getType().equals(LexicalState.ELSE)) {
            return condition;
        }

        if (!tokens.get(condition.newPosition).getType().equals(LexicalState.ELSE)) {
            throw new SyntaxException(tokens.get(condition.newPosition).getPosition(), "Ожидается \"else\"");
        }

        ASTItem elseExpression = this.scanExpression(condition.newPosition + 1);

        condition.append(false, elseExpression);
        condition.newPosition = elseExpression.newPosition;

        return condition;
    }


    public void analyze(List<Token> tokens) throws SyntaxException {
        this.ast.clear();
        this.tokens = tokens;

        for (int i = 0; i < tokens.size();) {
            ASTItem item = this.scanIf(i);
            i = item.newPosition;

            this.ast.add(item);
        }
    }


    public List<ASTItem> getAST() {
        return this.ast;
    }


    public String getASTString() {
        StringBuilder sb = new StringBuilder();

        for (ASTItem item: this.ast) {
            sb.append(item.toString());
            sb.append('\n');
        }

        return sb.toString();
    }


}
