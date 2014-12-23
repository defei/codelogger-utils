package org.codelogger.utils.component;

import org.codelogger.utils.StringUtils;

public class StringProcessChain {

  private static final ThreadLocal<StringProcessChain> stringProcessChains = new ThreadLocal<StringProcessChain>();

  private String source;

  private StringProcessChain() {

  }

  /**
   * @param source source string want to be handle.
   * @return a StringHandler instance for this source.
   */
  public static StringProcessChain getInstance(final String source) {

    StringProcessChain stringProcessChain = stringProcessChains.get();
    if (stringProcessChain == null) {
      stringProcessChain = new StringProcessChain();
      stringProcessChains.set(stringProcessChain);
    }
    stringProcessChain.source = source;
    return stringProcessChain;
  }

  /**
   * Trim all white space in the given string.<br>
   * If given source string self if it's null or length == 0 or not have white
   * space.
   * 
   * @return a new string has been removed all white space if given source
   *         string have white space; given source string self otherwise.
   */
  public StringProcessChain trimAllWhitespace() {

    if (!StringUtils.isEmpty(source) && StringUtils.containsWhitespace(source)) {
      int sourceLength = source.length();
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < sourceLength; i++) {
        char charAt = source.charAt(i);
        if (!Character.isWhitespace(charAt)) {
          stringBuilder.append(charAt);
        }
      }
      source = stringBuilder.toString();
    }
    return this;
  }

  /**
   * Replaces each substring of given source string that matches the given
   * regular expression ignore case sensitive with the given replacement.<br>
   * 
   * @param target target string want to be replaces.
   * @param replacement string of replace to.
   * @return a new string has been replaced each substring of given source
   *         string that matches the given regular expression ignore case
   *         sensitive with the given replacement.
   */
  public StringProcessChain replacIgnoreCase(final String target, final String replacement) {

    StringBuilder result = new StringBuilder();
    String temp = source;
    int indexOfIgnoreCase = 0;
    while (true) {
      indexOfIgnoreCase = StringUtils.indexOfIgnoreCase(temp, target);
      if (indexOfIgnoreCase == StringUtils.INDEX_OF_NOT_FOUND) {
        result.append(temp);
        break;
      }
      result.append(temp.substring(0, indexOfIgnoreCase));
      result.append(replacement);
      temp = temp.substring(indexOfIgnoreCase + target.length());
    }
    source = result.toString();
    return this;
  }

  /**
   * Returns a new string which source string delete destination chars ignore
   * case sensitive.
   * 
   * @param target target string want to be deleted.
   * @return a new string which source string delete destination chars ignore
   *         case sensitive.
   */
  public StringProcessChain deleteIgnoreCase(final String target) {

    return replacIgnoreCase(target, "");
  }

  public String getResult() {

    return source;
  }

}
