package com.coon.Lexical;


public enum LexicalState {
    NONE,
    SYMBOL,
    IDENTIFIER,
    CONST,
    HEX_OR_CONST,
    HEX,
    ERROR,
    END,
}
