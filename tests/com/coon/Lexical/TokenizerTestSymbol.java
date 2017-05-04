package com.coon.Lexical;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TokenizerTestSymbol extends AbstractTest {

    @Test
    void tokenize_1() throws ParsingException {
        this.tokenizer.tokenize("()=;", this.tokens);

        Assertions.assertEquals(3, this.tokens.size());

        Assertions.assertEquals("(", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(0).getType());

        Assertions.assertEquals(")", this.tokens.get(1).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(1).getType());

        Assertions.assertEquals("=", this.tokens.get(2).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(2).getType());
    }


    @Test
    void tokenize_2() throws ParsingException {
        this.tokenizer.tokenize("a = kek();", this.tokens);

        Assertions.assertEquals(5, this.tokens.size());

        Assertions.assertEquals("a", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(0).getType());

        Assertions.assertEquals("=", this.tokens.get(1).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(1).getType());

        Assertions.assertEquals("kek", this.tokens.get(2).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(2).getType());

        Assertions.assertEquals("(", this.tokens.get(3).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(3).getType());

        Assertions.assertEquals(")", this.tokens.get(4).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(4).getType());
    }

}
