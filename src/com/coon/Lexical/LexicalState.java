package com.coon.Lexical;


public enum LexicalState {
    NONE,
    IDENTIFIER,
    CONST,
    HEX_OR_CONST,
    HEX,
    ERROR,
    END,
}
