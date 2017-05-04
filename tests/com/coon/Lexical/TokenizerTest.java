package com.coon.Lexical;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TokenizerTest extends AbstractTest {

    @Test
    void tokenize_1() throws ParsingException {
        this.tokenizer.tokenize("if (lol) kek = 0 else kek = 0x5;", this.tokens);

        Assertions.assertEquals(11, this.tokens.size());

        Assertions.assertEquals("if", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.IF, this.tokens.get(0).getType());

        Assertions.assertEquals("(", this.tokens.get(1).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(1).getType());

        Assertions.assertEquals("lol", this.tokens.get(2).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(2).getType());

        Assertions.assertEquals(")", this.tokens.get(3).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(3).getType());

        Assertions.assertEquals("kek", this.tokens.get(4).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(4).getType());

        Assertions.assertEquals("=", this.tokens.get(5).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(5).getType());

        Assertions.assertEquals("0", this.tokens.get(6).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(6).getType());

        Assertions.assertEquals("else", this.tokens.get(7).getRead());
        Assertions.assertEquals(LexicalState.ELSE, this.tokens.get(7).getType());

        Assertions.assertEquals("kek", this.tokens.get(8).getRead());
        Assertions.assertEquals(LexicalState.IDENTIFIER, this.tokens.get(8).getType());

        Assertions.assertEquals("=", this.tokens.get(9).getRead());
        Assertions.assertEquals(LexicalState.SYMBOL, this.tokens.get(9).getType());

        Assertions.assertEquals("0x5", this.tokens.get(10).getRead());
        Assertions.assertEquals(LexicalState.HEX, this.tokens.get(10).getType());
    }

}
