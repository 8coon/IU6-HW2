package com.coon.Lexical;


public class Token {

    private String read;
    private LexicalState type;


    public Token(LexicalState type, String read) {
        this.type = type;
        this.read = read;
    }


    public String getRead() {
        return this.read;
    }


    public LexicalState getType() {
        return this.type;
    }

}
