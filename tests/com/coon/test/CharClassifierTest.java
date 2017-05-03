package com.coon.test;

import com.coon.CharClassifier.CharClassifier;
import com.coon.CharClassifier.CharType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;


/*

    describe("CharClassifier");

    it("should classify X char");
    assert(CharClassifier::classify('X') == X_SYM);
    assert(CharClassifier::classify('x') == X_SYM);

    it("should classify ZERO char");
    assert(CharClassifier::classify('0') == ZERO_SYM);

    it("should classify SIGN char");
    assert(CharClassifier::classify('+') == SIGN_SYM);
    assert(CharClassifier::classify('-') == SIGN_SYM);

    it("should classify DECIMAL char");
    for (char i = 1; i < 10;
         assert(CharClassifier::classify(i + '0') == DECIMAL_SYM), i++);

    it("should classify CHAR char");
    assert(CharClassifier::classify('_') == CHAR_SYM);
    assert(CharClassifier::classify('Y') == CHAR_SYM);
    assert(CharClassifier::classify('Z') == CHAR_SYM);
    assert(CharClassifier::classify('y') == CHAR_SYM);
    assert(CharClassifier::classify('z') == CHAR_SYM);
    for (char i = 'J'; i < 'X';
         assert(CharClassifier::classify(i) == CHAR_SYM), i++);
    for (char i = 'j'; i < 'x';
         assert(CharClassifier::classify(i) == CHAR_SYM), i++);

    it("should classify HEXCHAR char");
    for (char i = 'A'; i <= 'H';
         assert(CharClassifier::classify(i) == HEXCHAR_SYM), i++);
    for (char i = 'h'; i <= 'h';
         assert(CharClassifier::classify(i) == HEXCHAR_SYM), i++);

    it("should classify SPACE char");
    assert(CharClassifier::classify(' ') == SPACE_SYM);
    assert(CharClassifier::classify('\n') == SPACE_SYM);
    assert(CharClassifier::classify('\t') == SPACE_SYM);

    it("should classify END char");
    assert(CharClassifier::classify(';') == END_SYM);

 */


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