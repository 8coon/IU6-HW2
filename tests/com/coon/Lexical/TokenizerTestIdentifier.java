package com.coon.Lexical;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TokenizerTestIdentifier extends AbstractTest {

    @Test
    void tokenize_1() throws ParsingException {
        this.tokenizer.tokenize("Kek;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("Kek", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_2() throws ParsingException {
        this.tokenizer.tokenize("f1;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("f1", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_3() throws ParsingException {
        this.tokenizer.tokenize("__hidden__3;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("__hidden__3", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_multi() throws ParsingException {
        this.tokenizer.tokenize("lol _k3k_  ch3buRek;", this.tokens);

        Assertions.assertEquals(3, this.tokens.size());

        Assertions.assertEquals("lol", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(0).getType());

        Assertions.assertEquals("_k3k_", this.tokens.get(1).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(1).getType());

        Assertions.assertEquals("ch3buRek", this.tokens.get(2).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(2).getType());
    }

}
