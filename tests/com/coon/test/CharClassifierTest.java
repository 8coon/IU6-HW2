package com.coon.test;

import com.coon.CharClassifier.CharClassifier;
import com.coon.CharClassifier.CharType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class CharClassifierTest{

    @Test
    void classify() {
        Assertions.assertEquals(CharType.X, CharClassifier.classify('X'));
        Assertions.assertEquals(CharType.X, CharClassifier.classify('x'));

        Assertions.assertEquals(CharType.ZERO, CharClassifier.classify('0'));

        Assertions.assertEquals(CharType.SIGN, CharClassifier.classify('+'));
        Assertions.assertEquals(CharType.SIGN, CharClassifier.classify('-'));

        for (char i = '1'; i <= '9'; i++) {
            Assertions.assertEquals(CharType.DECIMAL, CharClassifier.classify(i));
        }

        Assertions.assertEquals(CharType.CHAR, CharClassifier.classify('_'));
        Assertions.assertEquals(CharType.CHAR, CharClassifier.classify('Y'));
        Assertions.assertEquals(CharType.CHAR, CharClassifier.classify('y'));
        Assertions.assertEquals(CharType.CHAR, CharClassifier.classify('Z'));
        Assertions.assertEquals(CharType.CHAR, CharClassifier.classify('z'));

        for (char i = 'J'; i < 'X'; i++) {
            Assertions.assertEquals(CharType.CHAR, CharClassifier.classify(i));
        }

        for (char i = 'j'; i < 'x'; i++) {
            Assertions.assertEquals(CharType.CHAR, CharClassifier.classify(i));
        }

        for (char i = 'A'; i <= 'H'; i++) {
            Assertions.assertEquals(CharType.HEX_CHAR, CharClassifier.classify(i));
        }

        for (char i = 'a'; i <= 'h'; i++) {
            Assertions.assertEquals(CharType.HEX_CHAR, CharClassifier.classify(i));
        }

        Assertions.assertEquals(CharType.SPACE, CharClassifier.classify(' '));
        Assertions.assertEquals(CharType.SPACE, CharClassifier.classify('\t'));
        Assertions.assertEquals(CharType.SPACE, CharClassifier.classify('\n'));

        Assertions.assertEquals(CharType.END, CharClassifier.classify(';'));

        Assertions.assertEquals(CharType.UNKNOWN, CharClassifier.classify('л'));
    }

}