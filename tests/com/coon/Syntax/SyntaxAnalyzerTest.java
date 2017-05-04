package com.coon.Syntax;

import com.coon.Lexical.ParsingException;
import com.coon.Lexical.Token;
import com.coon.Lexical.Tokenizer;
import com.coon.Syntax.AST.ASTItem;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;


class SyntaxAnalyzerTest {

    private List<Token> tokens = new ArrayList<>();
    private Tokenizer tokenizer = new Tokenizer();
    private SyntaxAnalyzer analyzer = new SyntaxAnalyzer();


    @BeforeEach
    public void beforeEach() {
        tokens.clear();
    }


    @AfterEach
    public void afterEach() {
        System.out.println(this.analyzer.getASTString());
    }


    @Test
    public void analyze_1() throws ParsingException, SyntaxException {
        tokenizer.tokenize("if (d) k = 0 else k = -0x5F; if (k) k = -1;", tokens);
        analyzer.analyze(tokens);

        Assertions.assertEquals(
                "" +
                        "(identifier) d?\n" +
                        " + Yes\n" +
                        " |  + =\n" +
                        " |  |  + (identifier) k\n" +
                        " |  |  + (constant) 0\n" +
                        " + No\n" +
                        " |  + =\n" +
                        " |  |  + (identifier) k\n" +
                        " |  |  + (hexadecimal constant) -0x5F\n" +
                        "\n" +
                        "(identifier) k?\n" +
                        " + Yes\n" +
                        " |  + =\n" +
                        " |  |  + (identifier) k\n" +
                        " |  |  + (constant) -1\n" +
                        " + No\n" +
                        "\n"
                , analyzer.getASTString()
        );
    }


    @Test
    public void analyze_2() throws ParsingException, SyntaxException {
        tokenizer.tokenize("if (a) if (b) if (c) if (d) k = 0 else k = -0x5F;", tokens);
        analyzer.analyze(tokens);

        Assertions.assertEquals(
                "" +
                        "(identifier) a?\n" +
                        " + Yes\n" +
                        " |  + (identifier) b?\n" +
                        " |  |  + Yes\n" +
                        " |  |  |  + (identifier) c?\n" +
                        " |  |  |  |  + Yes\n" +
                        " |  |  |  |  |  + (identifier) d?\n" +
                        " |  |  |  |  |  |  + Yes\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (identifier) k\n" +
                        " |  |  |  |  |  |  |  |  + (constant) 0\n" +
                        " |  |  |  |  |  |  + No\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (identifier) k\n" +
                        " |  |  |  |  |  |  |  |  + (hexadecimal constant) -0x5F\n" +
                        " |  |  |  |  + No\n" +
                        " |  |  + No\n" +
                        " + No\n" +
                        "\n"
                , analyzer.getASTString()
        );
    }


    @Test
    public void analyze_3() throws ParsingException, SyntaxException {
        tokenizer.tokenize("if (a) if (b) if (c) if (d) k = 0 else k = -0x5F;  \tif (k) k = -1;", tokens);
        analyzer.analyze(tokens);

        Assertions.assertEquals(
                "" +
                        "(identifier) a?\n" +
                        " + Yes\n" +
                        " |  + (identifier) b?\n" +
                        " |  |  + Yes\n" +
                        " |  |  |  + (identifier) c?\n" +
                        " |  |  |  |  + Yes\n" +
                        " |  |  |  |  |  + (identifier) d?\n" +
                        " |  |  |  |  |  |  + Yes\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (identifier) k\n" +
                        " |  |  |  |  |  |  |  |  + (constant) 0\n" +
                        " |  |  |  |  |  |  + No\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (identifier) k\n" +
                        " |  |  |  |  |  |  |  |  + (hexadecimal constant) -0x5F\n" +
                        " |  |  |  |  + No\n" +
                        " |  |  + No\n" +
                        " + No\n" +
                        "\n" +
                        "(identifier) k?\n" +
                        " + Yes\n" +
                        " |  + =\n" +
                        " |  |  + (identifier) k\n" +
                        " |  |  + (constant) -1\n" +
                        " + No\n" +
                        "\n"
                , analyzer.getASTString()
        );
    }

}