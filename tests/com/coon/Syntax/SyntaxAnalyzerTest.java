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
                        "(идентификатор) d?\n" +
                        " + Да\n" +
                        " |  + =\n" +
                        " |  |  + (идентификатор) k\n" +
                        " |  |  + (константа) 0\n" +
                        " + Нет\n" +
                        " |  + =\n" +
                        " |  |  + (идентификатор) k\n" +
                        " |  |  + (шестнадцатеричная константа) -0x5F\n" +
                        "\n" +
                        "(идентификатор) k?\n" +
                        " + Да\n" +
                        " |  + =\n" +
                        " |  |  + (идентификатор) k\n" +
                        " |  |  + (константа) -1\n" +
                        " + Нет\n" +
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
                        "(идентификатор) a?\n" +
                        " + Да\n" +
                        " |  + (идентификатор) b?\n" +
                        " |  |  + Да\n" +
                        " |  |  |  + (идентификатор) c?\n" +
                        " |  |  |  |  + Да\n" +
                        " |  |  |  |  |  + (идентификатор) d?\n" +
                        " |  |  |  |  |  |  + Да\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (идентификатор) k\n" +
                        " |  |  |  |  |  |  |  |  + (константа) 0\n" +
                        " |  |  |  |  |  |  + Нет\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (идентификатор) k\n" +
                        " |  |  |  |  |  |  |  |  + (шестнадцатеричная константа) -0x5F\n" +
                        " |  |  |  |  + Нет\n" +
                        " |  |  + Нет\n" +
                        " + Нет\n" +
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
                        "(идентификатор) a?\n" +
                        " + Да\n" +
                        " |  + (идентификатор) b?\n" +
                        " |  |  + Да\n" +
                        " |  |  |  + (идентификатор) c?\n" +
                        " |  |  |  |  + Да\n" +
                        " |  |  |  |  |  + (идентификатор) d?\n" +
                        " |  |  |  |  |  |  + Да\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (идентификатор) k\n" +
                        " |  |  |  |  |  |  |  |  + (константа) 0\n" +
                        " |  |  |  |  |  |  + Нет\n" +
                        " |  |  |  |  |  |  |  + =\n" +
                        " |  |  |  |  |  |  |  |  + (идентификатор) k\n" +
                        " |  |  |  |  |  |  |  |  + (шестнадцатеричная константа) -0x5F\n" +
                        " |  |  |  |  + Нет\n" +
                        " |  |  + Нет\n" +
                        " + Нет\n" +
                        "\n" +
                        "(идентификатор) k?\n" +
                        " + Да\n" +
                        " |  + =\n" +
                        " |  |  + (идентификатор) k\n" +
                        " |  |  + (константа) -1\n" +
                        " + Нет\n" +
                        "\n"
                , analyzer.getASTString()
        );
    }

}