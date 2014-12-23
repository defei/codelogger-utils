package org.codelogger.utils;

/**
 * @author DengDefei
 */
import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringUtilsTest {

  byte[] byteArray = { 'a', 'b', 'c', 'd', 'e' };

  short[] shortValueArray = { 'a', 'b', 'c', 'd', 'e' };

  char[] charArray = { 'a', 'b', 'c', 'd', 'e' };

  int[] intArray = { 1, 2, 3, 4, 5 };

  float[] floatArray = { 1.21F, 1.22F, 1.23F, 1.24F, 1.25F };

  long[] longArray = { 1L, 2L, 3L, 4L, 5L };

  double[] doubleArray = { 1.21D, 1.22D, 1.23D, 1.24D, 1.25D };

  boolean[] booleanArray = { true, false, true, true, false };

  Object[] objectArray = new Object[5];

  String[] stringArray = { "a", "b", "c", "d", "e" };

  private String source;

  private String target;

  @Test
  public void isBlank_parameterIsNull_returnTrue() {

    assertTrue(StringUtils.isBlank(source));
  }

  @Test
  public void isBlank_parameterIsEmpty_returnTrue() {

    source = "";
    assertTrue(StringUtils.isBlank(source));
  }

  @Test
  public void isBlank_parameterIsFullOfSpace_returnTrue() {

    source = "   ";
    assertTrue(StringUtils.isBlank(source));
  }

  @Test
  public void isBlank_parameterIsNotBlank_returnTrue() {

    source = "Test";
    assertFalse(StringUtils.isBlank(source));
  }

  @Test
  public void isNotBlank_parameterIsNull_returnTrue() {

    assertFalse(StringUtils.isNotBlank(source));
  }

  @Test
  public void isNotBlank_parameterIsEmpty_returnTrue() {

    source = "";
    assertFalse(StringUtils.isNotBlank(source));
  }

  @Test
  public void isNotBlank_parameterIsFullOfSpace_returnTrue() {

    source = "   ";
    assertFalse(StringUtils.isNotBlank(source));
  }

  @Test
  public void isNotBlank_parameterIsCorrect_returnTrue() {

    source = "Test";
    assertTrue(StringUtils.isNotBlank(source));
  }

  @Test
  public void firstCharToUpperCase_blankString_returnCorrectValue() {

    source = "    ";
    String firstCharToUpperCase = StringUtils.firstCharToUpperCase(source);
    assertEquals(source, firstCharToUpperCase);
  }

  @Test
  public void firstCharToUpperCase_parameterIsCorrect_returnCorrectValue() {

    source = "stringWantToFormart";
    String exceptString = "StringWantToFormart";
    String firstCharToUpperCase = StringUtils.firstCharToUpperCase(source);
    assertEquals(exceptString, firstCharToUpperCase);
  }

  @Test
  public void firstCharToUpperCase_parameterIsCorrect_returnCorrectValue1() {

    source = "StringWantToFormart";
    String exceptString = "StringWantToFormart";
    String firstCharToUpperCase = StringUtils.firstCharToUpperCase(source);
    assertEquals(exceptString, firstCharToUpperCase);
  }

  @Test
  public void firstCharToLowerCase_sourceStringIsEmpty_returnCorrectValue() {

    source = "";
    String firstCharToUpperCase = StringUtils.firstCharToLowerCase(source);
    assertEquals(source, firstCharToUpperCase);
  }

  @Test
  public void firstCharToLowerCase_parameterIsCorrect_returnCorrectValue() {

    source = "StringWantToFormart";
    String exceptString = "stringWantToFormart";
    String firstCharToUpperCase = StringUtils.firstCharToLowerCase(source);
    assertEquals(exceptString, firstCharToUpperCase);
  }

  @Test
  public void firstCharToLowerCase_parameterIsCorrect_returnCorrectValue1() {

    source = "stringWantToFormart";
    String exceptString = "stringWantToFormart";
    String firstCharToUpperCase = StringUtils.firstCharToLowerCase(source);
    assertEquals(exceptString, firstCharToUpperCase);
  }

  @Test
  public void indexOfIgnoreCase_sourceStringIsBlank_throwIllegalArgumentException() {

    int expectIndex = -1;
    source = "  ";
    target = "aa";
    int resultIndex = StringUtils.indexOfIgnoreCase(source, target);
    assertEquals(expectIndex, resultIndex);
  }

  @Test
  public void indexOfIgnoreCase_parameterIsCorrect_returnIndex() {

    int expectIndex = 2;
    source = "I am not blank";
    String source1 = "I AM not blank";
    target = "AM";
    int resultIndex = StringUtils.indexOfIgnoreCase(source, target);
    assertEquals(expectIndex, resultIndex);
    int resultIndex1 = StringUtils.indexOfIgnoreCase(source1, target);
    assertEquals(expectIndex, resultIndex1);
  }

  @Test
  public void indexOfIgnoreCase_withStartIndexCase1_returnIndex() {

    int expectIndex = 12;
    source = "I am not blank";
    String source1 = "I AM not blank";
    target = "n";
    int resultIndex = StringUtils.indexOfIgnoreCase(source, target, 6);
    assertEquals(expectIndex, resultIndex);
    int resultIndex1 = StringUtils.indexOfIgnoreCase(source1, target, 6);
    assertEquals(expectIndex, resultIndex1);
  }

  @Test
  public void indexOfIgnoreCase_withStartIndexCase2_returnIndex() {

    int expectIndex = 12;
    source = "I am not blank";
    String source1 = "I AM not blank";
    target = "N";
    int resultIndex = StringUtils.indexOfIgnoreCase(source, target, 6);
    assertEquals(expectIndex, resultIndex);
    int resultIndex1 = StringUtils.indexOfIgnoreCase(source1, target, 6);
    assertEquals(expectIndex, resultIndex1);
  }

  @Test
  public void containsWhitespace_sourceStringHasWhiteSpance_returnTrue() {

    source = "I am just for test!";
    assertTrue(StringUtils.containsWhitespace(source));
  }

  @Test
  public void containsWhitespace_sourceStringHasWhiteSpance_returnTrue1() {

    source = "I\nam_just_for_test!";
    assertTrue(StringUtils.containsWhitespace(source));
  }

  @Test
  public void containsWhitespace_sourceStringHasWhiteSpance_returnTrue2() {

    source = "I\tam_just_for_test!";
    assertTrue(StringUtils.containsWhitespace(source));
  }

  @Test
  public void containsWhitespace_sourceStringNotHasWhiteSpance_returnTrue() {

    source = "I_am_just_for_test!";
    assertFalse(StringUtils.containsWhitespace(source));
  }

  @Test
  public void containsIgnoreCase() {

    boolean containsIgnoreCase = StringUtils.containsIgnoreCase(source, target);
    assertFalse(containsIgnoreCase);

    source = "";
    containsIgnoreCase = StringUtils.containsIgnoreCase(source, target);
    assertFalse(containsIgnoreCase);

    source = "testString";
    target = "aaa";
    containsIgnoreCase = StringUtils.containsIgnoreCase(source, target);
    assertFalse(containsIgnoreCase);

    source = "testString";
    target = "tSt";
    containsIgnoreCase = StringUtils.containsIgnoreCase(source, target);
    assertTrue(containsIgnoreCase);

    source = "testString";
    target = "TST";
    containsIgnoreCase = StringUtils.containsIgnoreCase(source, target);
    assertTrue(containsIgnoreCase);
  }

  @Test
  public void trimAllWhitespace_paramenterIsCorrect_returnCorrectValue() {

    String expect = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaIamjustfortest!";
    source = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  I am\njust\tfor test!  ";
    String trimAllWhitespace = StringUtils.trimAllWhitespace(source);
    assertEquals(expect, trimAllWhitespace);
  }

  @Test
  public void trimAllWhitespace_paramenterIsCorrect_returnCorrectValue1() {

    String expect = "";
    source = "";
    String trimAllWhitespace = StringUtils.trimAllWhitespace(source);
    assertEquals(expect, trimAllWhitespace);
  }

  @Test
  public void trimAllWhitespace_paramenterIsCorrect_returnCorrectValue2() {

    String expect = "I_am_just_for_test!I_am_just_for_test!I_am_just_for_test!";
    source = "I_am_just_for_test!I_am_just_for_test!I_am_just_for_test!";
    String trimAllWhitespace = StringUtils.trimAllWhitespace(source);
    assertEquals(expect, trimAllWhitespace);
  }

  @Test
  public void startsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue() {

    String starts = "i Am";
    source = "I am just for test!";
    assertTrue(StringUtils.startsWithIgnoreCase(source, starts));
  }

  @Test
  public void startsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue1() {

    String starts = "I am";
    source = "I am just for test!";
    assertTrue(StringUtils.startsWithIgnoreCase(source, starts));
  }

  @Test
  public void startsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue2() {

    String starts = "i_am";
    source = "I am just for test!";
    assertFalse(StringUtils.startsWithIgnoreCase(source, starts));
  }

  @Test
  public void startsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue3() {

    String starts = "i_am";
    source = "I am just for test!";
    assertFalse(StringUtils.startsWithIgnoreCase(starts, source));
  }

  @Test
  public void endsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue() {

    String ends = "or TesT!";
    source = "I am just for test!";
    assertTrue(StringUtils.endsWithIgnoreCase(source, ends));
  }

  @Test
  public void endsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue1() {

    String ends = "or test!";
    source = "I am just for test!";
    assertTrue(StringUtils.endsWithIgnoreCase(source, ends));
  }

  @Test
  public void endsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue2() {

    String ends = "or_Test!";
    source = "I am just for test!";
    assertFalse(StringUtils.endsWithIgnoreCase(source, ends));
  }

  @Test
  public void endsWithIgnoreCase_paramenterIsCorrect_returnCorrectValue3() {

    String ends = "or_Test!";
    source = "I am just for test!";
    assertFalse(StringUtils.endsWithIgnoreCase(ends, source));
  }

  @Test
  public void getRandomAlphabeticString() {

    Set<String> set = new HashSet<String>();
    for (int i = 0; i < 100; i++) {
      String randomString = StringUtils.getRandomString(10);
      set.add(randomString);
      System.out.println(randomString);
    }
    assertEquals(100, set.size());
  }

  @Test
  public void getRandomUpperCaseAlphabetic() {

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 100; i++) {
      char randomUpperCaseAlphabetic = StringUtils.getRandomUpperCaseAlphabetic();
      sb.append(randomUpperCaseAlphabetic);
    }
    String string = sb.toString();
    assertTrue(Pattern.matches("^[A-Z]+$", string));
  }

  @Test
  public void getRandomLowerCaseAlphabetic() {

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 100; i++) {
      char randomUpperCaseAlphabetic = StringUtils.getRandomLowerCaseAlphabetic();
      sb.append(randomUpperCaseAlphabetic);
    }
    String string = sb.toString();
    assertTrue(Pattern.matches("^[a-z]+$", string));
  }

  @Test
  public void getRandomAlphabetic() {

    Set<Character> set = new TreeSet<Character>();
    for (int i = 0; i < 10000; i++) {
      char randomChar = StringUtils.getRandomAlphabetic();
      set.add(randomChar);
    }
    assertEquals(52, set.size());
    for (Character character : set) {
      assertTrue(Pattern.matches("[a-zA-Z]", character.toString()));
    }
  }

  @Test
  public void getRandomPasswordChar() {

    Set<Character> set = new TreeSet<Character>();
    for (int i = 0; i < 10000; i++) {
      char randomChar = StringUtils.getRandomPasswordChar();
      set.add(randomChar);
    }
    assertEquals(62, set.size());
    for (Character character : set) {
      assertTrue(Pattern.matches("[a-zA-Z0-9]", character.toString()));
    }
  }

  @Test
  public void encoding() {

    String test = "你好";
    println(test);
    String gbkTest = StringUtils.encoding(test, "utf-8", "gbk");
    assertNotSame(test, gbkTest);
    println(gbkTest);
    String utf8Test = StringUtils.encoding(gbkTest, "gbk", "utf-8");
    assertEquals(test, utf8Test);
    println(utf8Test);
    try {
      StringUtils.encoding(gbkTest, "gbk", "utf21");
    } catch (Exception e) {
      assertEquals(IllegalArgumentException.class, e.getClass());
    }
  }

  @Test
  public void getWords() {

    String testText = "Dear Sam, I am very1 very very siad to her that.";
    int wordMinimumLength = 1;

    List<String> words = StringUtils.getWords(testText, wordMinimumLength);
    println(words);
    assertEquals(11, words.size());

    wordMinimumLength = 2;
    words = StringUtils.getWords(testText, wordMinimumLength);
    println(words);
    assertEquals(10, words.size());

    wordMinimumLength = 3;
    words = StringUtils.getWords(testText, wordMinimumLength);
    println(words);
    assertEquals(8, words.size());

    words = StringUtils.getWords(null, 1);
    assertEquals(0, words.size());
  }

  @Test
  public void containsWord() {

    String testText = "Oh,sorry,Dear Sam. Sorry, I am very1 1very very very siad to her that.";
    String testContainedWord = "Dear";
    String testContainedWord1 = "ear";
    String testNotContainedWord = "dear";
    boolean isContained = StringUtils.containsWord(testText, testContainedWord);
    assertTrue(isContained);
    isContained = StringUtils.containsWord(testText, testContainedWord1);
    assertFalse(isContained);
    isContained = StringUtils.containsWord(testText, testNotContainedWord);
    assertFalse(isContained);
    isContained = StringUtils.containsWord(null, testNotContainedWord);
    assertFalse(isContained);
    isContained = StringUtils.containsWord(testText, null);
    assertFalse(isContained);
  }

  @Test
  public void containsWordIgnoreCase() {

    String testText = "Oh,sorry,Dear Sam. Sorry, I am very1 1very very very siad to her that.";
    String testContainedWord = "Dear";
    String testContainedWord1 = "dear";
    boolean isContained = StringUtils.containsWordIgnoreCase(testText, testContainedWord);
    assertTrue(isContained);
    isContained = StringUtils.containsWordIgnoreCase(testText, testContainedWord1);
    assertTrue(isContained);
    isContained = StringUtils.containsWordIgnoreCase(null, testContainedWord);
    assertFalse(isContained);
    isContained = StringUtils.containsWordIgnoreCase(testText, null);
    assertFalse(isContained);
  }

  @Test
  public void reverse() {

    String expectedString = "cba";
    String testString = "abc";
    println(testString);
    String reverse = StringUtils.reverse(testString);
    println(reverse);
    assertEquals(expectedString, reverse);
    reverse = StringUtils.reverse(null);
    assertEquals(null, reverse);
  }

  @Test
  public void count() {

    String expectedString = "a|b|c";
    String testString = "a|b|c";
    String target = "|";
    println(testString);
    int count = StringUtils.count(testString, target);
    assertEquals(expectedString, testString);
    assertEquals(2, count);
  }

  @Test
  public void countIgnoreCase() {

    String expectedString = "A|b|a";
    String testString = "A|b|a";
    String target = "a";
    println(testString);
    int count = StringUtils.countIgnoreCase(testString, target);
    assertEquals(expectedString, testString);
    assertEquals(2, count);
  }

  @Test
  public void getStringsByRegex() {

    int expectedSize = 2;
    String expectedString = "<a href='#'>H</a>";
    String stringToBeTest = "<body><a href='#'>H</a><br/>\n<a href='#'>H</a></body>";
    List<String> links = StringUtils.getStringsByRegex(stringToBeTest, "<a.*>H</a>");
    assertEquals(expectedSize, links.size());
    assertEquals(expectedString, links.get(0));
  }

  @Test
  public void startStringProcess_getInstance() {

    assertNotNull(StringUtils.startStringProcess(source));
  };
}
