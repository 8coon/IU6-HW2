package com.coon.Lexical;


public class Token {

    private String read;
    private LexicalState type;
    private int position;


    public Token(LexicalState type, String read, int position) {
        switch (type) {
            case HEX_OR_CONST: {
                type = LexicalState.CONST;
            } break;

            case IDENTIFIER: {
                if (read.equals("if")) {
                    type = LexicalState.IF;
                } else if (read.equals("else")) {
                    type = LexicalState.ELSE;
                }
            } break;
        }

        this.type = type;
        this.read = read;
        this.position = position;
    }


    public String getRead() {
        return this.read;
    }


    public LexicalState getType() {
        return this.type;
    }


    public int getPosition() {
        return this.position;
    }

}
