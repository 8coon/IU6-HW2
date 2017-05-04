package com.coon.Lexical;


import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;


public class AbstractTest {

    protected List<Token> tokens = new ArrayList<>();
    protected Tokenizer tokenizer = new Tokenizer();


    @BeforeEach
    public void beforeEach() {
        this.tokens.clear();
        this.tokenizer.setLogging(true);
    }

}
