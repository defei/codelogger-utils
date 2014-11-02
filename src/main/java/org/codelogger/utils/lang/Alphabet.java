package org.codelogger.utils.lang;

/**
 * 字母数值
 * 
 * @author DengDefei
 * 
 */
public enum Alphabet {

    A("A", 2), B("B", 3), C("C", 5), D("D", 7), E("E", 11), F("F", 13), G("G", 17), H("H", 19), I(
            "I", 23), J("J", 29), K("K", 31), L("L", 37), M("M", 41), N("N", 43), O("O", 47), P(
            "P", 53), Q("Q", 59), R("R", 61), S("S", 67), T("T", 71), U("U", 73), V("V", 79), W(
            "W", 83), X("X", 89), Y("Y", 97), Z("Z", 101);

    private final String character;

    private final int primeNumber;

    private Alphabet(final String character, final int primeNumber) {

        this.character = character;
        this.primeNumber = primeNumber;
    }

    public String getKey() {

        return character;
    }

    public int getValue() {

        return primeNumber;
    }
}