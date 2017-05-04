package com.coon.Lexical;


import com.coon.CharClassifier.CharClassifier;
import com.coon.CharClassifier.CharType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tokenizer {

    private Map<LexicalState, Map<CharType, LexicalState>> stateJumps;
    private boolean logging = false;


    private Map<LexicalState, Map<CharType, LexicalState>> initStateJumps() {
        final Map<LexicalState, Map<CharType, LexicalState>> map = new HashMap<>();

        map.put(LexicalState.NONE, new HashMap<CharType, LexicalState>() {{
            put(CharType.SIGN,     LexicalState.HEX_OR_CONST);
            put(CharType.CHAR,     LexicalState.IDENTIFIER);
            put(CharType.ZERO,     LexicalState.HEX_OR_CONST);
            put(CharType.X,        LexicalState.IDENTIFIER);
            put(CharType.DECIMAL,  LexicalState.CONST);
            put(CharType.SPACE,    LexicalState.END);
            put(CharType.HEX_CHAR, LexicalState.IDENTIFIER);
        }});

        map.put(LexicalState.IDENTIFIER, new HashMap<CharType, LexicalState>() {{
            put(CharType.SIGN,     LexicalState.ERROR);
            put(CharType.CHAR,     LexicalState.IDENTIFIER);
            put(CharType.ZERO,     LexicalState.IDENTIFIER);
            put(CharType.X,        LexicalState.IDENTIFIER);
            put(CharType.DECIMAL,  LexicalState.IDENTIFIER);
            put(CharType.SPACE,    LexicalState.END);
            put(CharType.HEX_CHAR, LexicalState.IDENTIFIER);
        }});

        map.put(LexicalState.CONST, new HashMap<CharType, LexicalState>() {{
            put(CharType.SIGN,     LexicalState.ERROR);
            put(CharType.CHAR,     LexicalState.ERROR);
            put(CharType.ZERO,     LexicalState.CONST);
            put(CharType.X,        LexicalState.ERROR);
            put(CharType.DECIMAL,  LexicalState.CONST);
            put(CharType.SPACE,    LexicalState.END);
            put(CharType.HEX_CHAR, LexicalState.ERROR);
        }});

        map.put(LexicalState.HEX, new HashMap<CharType, LexicalState>() {{
            put(CharType.SIGN,     LexicalState.ERROR);
            put(CharType.CHAR,     LexicalState.ERROR);
            put(CharType.ZERO,     LexicalState.HEX);
            put(CharType.X,        LexicalState.ERROR);
            put(CharType.DECIMAL,  LexicalState.HEX);
            put(CharType.SPACE,    LexicalState.END);
            put(CharType.HEX_CHAR, LexicalState.HEX);
        }});

        map.put(LexicalState.HEX_OR_CONST, new HashMap<CharType, LexicalState>() {{
            put(CharType.SIGN,     LexicalState.ERROR);
            put(CharType.CHAR,     LexicalState.ERROR);
            put(CharType.ZERO,     LexicalState.HEX_OR_CONST);
            put(CharType.X,        LexicalState.HEX);
            put(CharType.DECIMAL,  LexicalState.CONST);
            put(CharType.SPACE,    LexicalState.END);
            put(CharType.HEX_CHAR, LexicalState.ERROR);
        }});

        return map;
    }


    private void log(String str) {
        System.out.println(str);
    }


    public void tokenize(String in, List<Token> out) throws ParsingException {
        StringBuilder read = new StringBuilder();
        LexicalState state = LexicalState.NONE;

        this.log("Started parsing \"" + in + "\"");

        for (int i = 0; i < in.length(); i++) {
            char ch = in.charAt(i);

            CharType type = CharClassifier.classify(ch);
            LexicalState newState;
            Character appendChar = null;

            if (type == CharType.UNKNOWN) {
                newState = null;
                appendChar = ch;
            } else {
                newState = this.stateJumps.get(state).get(type);
            }

            if (newState == null) {
                newState = LexicalState.END;
            }

            this.log(String.valueOf(state) + " to " + String.valueOf(newState) + " on " + String.valueOf(type));

            switch (newState) {
                case ERROR: {
                    throw new ParsingException(i);
                }

                case END: {
                    if (state != LexicalState.NONE) {
                        out.add(new Token(state, read.toString()));

                        read = new StringBuilder();
                        state = LexicalState.NONE;
                    }

                    if (appendChar != null) {
                        out.add(new Token(LexicalState.SYMBOL, String.valueOf(appendChar)));
                    }
                } break;

                default: {
                    read.append(ch);
                    state = newState;
                } break;
            }
        }
    }


    public Tokenizer() {
        this.stateJumps = initStateJumps();
    }


    public boolean isLogging() {
        return this.logging;
    }


    public void setLogging(final boolean value) {
        this.logging = value;
    }

}
