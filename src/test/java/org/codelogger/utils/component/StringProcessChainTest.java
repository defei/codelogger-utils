package org.codelogger.utils.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class StringProcessChainTest {

  @Test
  public void trimAllWhitespace_sourceIsEmpty_returnSource() {

    String source = "";
    String trimAllWhitespace = StringProcessChain.getInstance(source).trimAllWhitespace()
      .getResult();
    assertEquals(source, trimAllWhitespace);
  }

  @Test
  public void trimAllWhitespace_sourceIsBlank_returnEmptyString() {

    String expect = "";
    String source = "  ";
    String trimAllWhitespace = StringProcessChain.getInstance(source).trimAllWhitespace()
      .getResult();
    assertEquals(expect, trimAllWhitespace);
  }

  @Test
  public void trimAllWhitespace_sourceNotEmptyAndNotContainsWhitespace_returnSource() {

    String source = "hello";
    String trimAllWhitespace = StringProcessChain.getInstance(source).trimAllWhitespace()
      .getResult();
    assertEquals(source, trimAllWhitespace);
  }

  @Test
  public void trimAllWhitespace_sourceIsNotEmptyAndContainsWithspace_returnCorrectValue() {

    String expect = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaIamjustfortest!";
    String source = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  I am\njust\tfor test!  ";
    String trimAllWhitespace = StringProcessChain.getInstance(source).trimAllWhitespace()
      .getResult();
    assertEquals(expect, trimAllWhitespace);
  }

  @Test
  public void trimAllWhitespace_paramenterIsCorrect_returnCorrectValue2() {

    String expect = "I_am_just_for_test!I_am_just_for_test!I_am_just_for_test!";
    String source = "I_am_just_for_test!I_am_just_for_test!I_am_just_for_test!";
    String trimAllWhitespace = StringProcessChain.getInstance(source).trimAllWhitespace()
      .getResult();
    assertEquals(expect, trimAllWhitespace);
  }

  @Test
  public void replacIgnoreCase_paramenterIsCorrect_returnCorrectValue() {

    String expect = "I am king of and king of test!";
    String source = "I am JusT for and Just FOR test!";
    String target = "just for";
    String replacement = "king of";
    String result = StringProcessChain.getInstance(source).replacIgnoreCase(target, replacement)
      .getResult();
    assertEquals(expect, result);
  }

  @Test
  public void replacIgnoreCase_paramenterIsCorrect_returnCorrectValue1() {

    String source = "I am JusT for and Just FOR test!";
    String target = "just for1";
    String replacement = "king of";
    String result = StringProcessChain.getInstance(source).replacIgnoreCase(target, replacement)
      .getResult();
    assertEquals(source, result);
  }

  @Test
  public void deleteIgnoreCase_paramenterIsCorrect_returnCorrectValue() {

    String expect = "I am juor juor test!";
    String destination = "sT f";
    String source = "I am just for just for test!";
    String deleteIgnoreCase = StringProcessChain.getInstance(source).deleteIgnoreCase(destination)
      .getResult();
    assertEquals(expect, deleteIgnoreCase);
  }

  @Test
  public void deleteIgnoreCase_paramenterIsCorrect_returnCorrectValue1() {

    String expect = "I am juor test!";
    String destination = "sTaf";
    String source = "I am just for test!";
    String deleteIgnoreCase = StringProcessChain.getInstance(source).deleteIgnoreCase(destination)
      .getResult();
    assertFalse(expect.equals(deleteIgnoreCase));
  }

  @Test
  public void finalTest() {

    String expected = "Iamjuandtest!";
    String destination = "sT f";
    String source = "I am just for test!";
    String actual = StringProcessChain.getInstance(source).deleteIgnoreCase(destination)
      .trimAllWhitespace().replacIgnoreCase("or", "and").getResult();
    assertEquals(expected, actual);
  }
}
