package com.coon.Lexical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
// import org.junit.jupiter.api.Assertions.*;


class TokenizerTest {

    private List<Token> tokens = new ArrayList<>();
    private Tokenizer tokenizer = new Tokenizer();


    @BeforeEach
    void before() {
        this.tokens.clear();
    }


    @Test
    void tokenize_empty() throws ParsingException {
        this.tokenizer.tokenize("", this.tokens);
        Assertions.assertEquals(0, this.tokens.size());
    }


    @Test
    void tokenize_const() throws ParsingException {
        this.tokenizer.tokenize("2", this.tokens);

        Assertions.assertEquals(1, this.tokens.size());
        Assertions.assertEquals("2", this.tokens.get(0).getRead());
        Assertions.assertEquals(LexicalState.CONST, this.tokens.get(0).getType());
    }

}