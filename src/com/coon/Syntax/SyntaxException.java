package com.coon.Syntax;


public class SyntaxException extends Exception {

    public SyntaxException(int position, String message) {
        super("Syntax error at " + String.valueOf(position) + ": \"" + message + "\"");
    }

}
