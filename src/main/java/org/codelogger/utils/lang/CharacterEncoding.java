package org.codelogger.utils.lang;

public enum CharacterEncoding {

  UTF_8("utf-8");

  private String charsetName;

  private CharacterEncoding(final String charsetName) {

    this.charsetName = charsetName;
  }

  public String getCharsetName() {

    return charsetName;
  }
}
