package com.coon.CharClassifier;


public class CharClassifier {

    private static boolean isX(char ch) {
        return ch == 'x' || ch == 'X';
    }


    private static boolean isZero(char ch) {
        return ch == '0';
    }


    private static boolean isSign(char ch) {
        return ch == '+' || ch == '-';
    }


    private static boolean isDecimal(char ch) {
        return ch >= '0' && ch <= '9';
    }


    private static boolean isChar(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || ch == '_';
    }


    private static boolean isHexChar(char ch) {
        return (ch >= 'A' && ch <= 'H') || (ch >= 'a' && ch <= 'h');
    }


    private static boolean isSpace(char ch) {
        return ch == ' ' || ch == '\n' || ch == '\t';
    }


    private static boolean isEnd(char ch) {
        return ch == ';';
    }


    private static boolean isEquation(char ch) {
        return ch == '=';
    }


    public static CharType classify(char ch) {
        if (CharClassifier.isEnd(ch)) return CharType.END;
        if (CharClassifier.isSign(ch)) return CharType.SIGN;
        if (CharClassifier.isEquation(ch)) return CharType.EQUATION;
        if (CharClassifier.isZero(ch)) return CharType.ZERO;
        if (CharClassifier.isX(ch)) return CharType.X;
        if (CharClassifier.isSpace(ch)) return CharType.SPACE;
        if (CharClassifier.isDecimal(ch)) return CharType.DECIMAL;
        if (CharClassifier.isHexChar(ch)) return CharType.HEX_CHAR;
        if (CharClassifier.isChar(ch)) return CharType.CHAR;

        return CharType.UNKNOWN;
    }


}
