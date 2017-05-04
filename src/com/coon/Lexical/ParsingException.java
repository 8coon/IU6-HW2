package com.coon.Lexical;


public class ParsingException extends Exception {

    public ParsingException(int position) {
        super("Ошибка лексического анализа на позиции " + String.valueOf(position));
    }

}
