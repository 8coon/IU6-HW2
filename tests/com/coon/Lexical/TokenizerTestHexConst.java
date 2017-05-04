package com.coon.Lexical;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TokenizerTestHexConst extends AbstractTest{

    @Test
    void tokenize_1() throws ParsingException {
        this.tokenizer.tokenize("0x2;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("0x2", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.HEX, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_2() throws ParsingException {
        this.tokenizer.tokenize("-0x255FF;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("-0x255FF", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.HEX, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_multi() throws ParsingException {
        this.tokenizer.tokenize("0x25AF +0x00FF  -00x355DEAD;", this.tokens);

        Assertions.assertEquals(3, this.tokens.size());

        Assertions.assertEquals("0x25AF", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.HEX, this.tokens.get(0).getType());

        Assertions.assertEquals("+0x00FF", this.tokens.get(1).getRead());
        Assertions.assertEquals(LexicalState.HEX, this.tokens.get(1).getType());

        Assertions.assertEquals("-00x355DEAD", this.tokens.get(2).getRead());
        Assertions.assertEquals(LexicalState.HEX, this.tokens.get(2).getType());
    }
}
