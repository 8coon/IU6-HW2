package com.coon;


import com.coon.Lexical.ParsingException;
import com.coon.Lexical.Token;
import com.coon.Lexical.Tokenizer;
import com.coon.Syntax.SyntaxAnalyzer;
import com.coon.Syntax.SyntaxException;

import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static void intro() {
        System.out.println("Домашнее задание №2");
        System.out.println("Пешков Сергей, ИУ6-43");
        System.out.println("Вариант 20");
        System.out.println("2017");

        System.out.println("");
        System.out.println("Вводите выражения. Для завершения работы введите \"exit();\"");
    }


    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer();
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();

        tokenizer.setLogging(false);

        Main.intro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();

            if (line.equals("exit();")) {
                System.exit(0);
            }

            tokens.clear();

            try {
                tokenizer.tokenize(line, tokens);
            } catch (ParsingException e) {
                System.err.println(e.getMessage());
                System.out.println("Конструкция не распознана!");

                continue;
            }

            try {
                analyzer.analyze(tokens);
            } catch (SyntaxException e) {
                System.err.println(e.getMessage());
                System.out.println("Конструкция не распознана!");

                continue;
            }

            System.out.print("\n" + analyzer.getASTString());
            System.out.println("Конструкция распознана.");
        }
    }

}

