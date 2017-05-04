package com.coon.Syntax;


public class SyntaxException extends Exception {

    public SyntaxException(int position, String message) {
        super("Ошибка синтаксического анализа на позиции " + String.valueOf(position) + ": \"" + message + "\"");
    }

}
