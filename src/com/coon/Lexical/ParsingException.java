package com.coon.Lexical;


public class ParsingException extends Exception {

    public ParsingException(int position) {
        super("Error at " + String.valueOf(position));
    }

}
