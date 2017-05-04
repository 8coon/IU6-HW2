package com.coon.Lexical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TokenizerTestConst extends AbstractTest {

    @Test
    void tokenize_1() throws ParsingException {
        this.tokenizer.tokenize("2;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("2", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_2() throws ParsingException {
        this.tokenizer.tokenize("+2;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("+2", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_3() throws ParsingException {
        this.tokenizer.tokenize("-252;", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("-252", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(0).getType());
    }


    @Test
    void tokenize_multi() throws ParsingException {
        this.tokenizer.tokenize("252 +23  -355;", this.tokens);

        Assertions.assertEquals(3, this.tokens.size());

        Assertions.assertEquals("252", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(0).getType());

        Assertions.assertEquals("+23", this.tokens.get(1).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(1).getType());

        Assertions.assertEquals("-355", this.tokens.get(2).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(2).getType());
    }

}