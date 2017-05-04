package com.coon.Syntax.AST;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public abstract class ASTItem {

    private List<ASTItem> children = new ArrayList<>();
    public int newPosition = 0;


    public abstract String toStringInfo();


    @NotNull
    private String[] toStrings() {
        List<String> strings = new ArrayList<>();

        strings.add(this.toStringInfo());

        for (ASTItem child: this.children) {
            final String[] childStrings = child.toStrings();
            strings.add(" + " + childStrings[0]);

            for (int i = 1; i < childStrings.length; i++) {
                strings.add(" | " + childStrings[i]);
            }
        }

        return strings.toArray(new String[0]);
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final String[] strings = this.toStrings();

        for (String str: strings) {
            sb.append(str);
            sb.append('\n');
        }

        return sb.toString();
    }


    public List<ASTItem> getChildren() {
        return this.children;
    }

}
