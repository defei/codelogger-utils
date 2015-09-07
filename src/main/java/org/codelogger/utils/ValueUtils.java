package org.codelogger.utils;

import static org.codelogger.utils.StringUtils.isBlank;

/**
 * A useful tools to handle numbers.
 *
 * @author DengDefei
 */
public class ValueUtils {

  private ValueUtils() {

  }

  public static Byte getByte(final Byte value, final Byte defaultValue) {

    return value == null ? defaultValue : value;
  }

  /**
   * Returns 0 if given value is null; else returns it self.
   *
   * @param value value to be test.
   * @return 0 if given value is null; else returns it self.
   */
  public static Long getValue(final Long value) {

    return getValue(value, 0L);
  }

  /**
   * Returns defaultValue if given value is null; else returns it self.
   *
   * @param value value to be test.
   * @param defaultValue default value to return if given value is null.
   * @return defaultValue if given value is null; else returns it self.
   */
  public static Long getValue(final Long value, final Long defaultValue) {

    return value == null ? defaultValue : value;
  }

  /**
   * Returns 0 if given value is null; else returns it self.
   *
   * @param value value to be test.
   * @return 0 if given value is null; else returns it self.
   */
  public static Integer getValue(final Integer value) {

    return getValue(value, 0);
  }

  public static Integer getValue(final Integer value, final Integer defaultValue) {

    return value == null ? defaultValue : value;
  }

  /**
   * Returns 0 if given value is null; else returns it self.
   *
   * @param value value to be test.
   * @return 0 if given value is null; else returns it self.
   */
  public static Double getValue(final Double value) {

    return getValue(value, 0D);
  }

  public static Double getValue(final Double value, final Double defaultValue) {

    return value == null ? defaultValue : value;
  }

  /**
   * Returns 0 if given value is null; else returns it self.
   *
   * @param value value to be test.
   * @return 0 if given value is null; else returns it self.
   */
  public static Float getValue(final Float value) {

    return getValue(value, 0F);
  }

  public static Float getValue(final Float value, final Float defaultValue) {

    return value == null ? defaultValue : value;
  }

  /**
   * Returns 0 if given value is null; else returns it self.
   *
   * @param value value to be test.
   * @return 0 if given value is null; else returns it self.
   */
  public static Number getValue(final Number value) {

    return getValue(value, 0);
  }

  public static Number getValue(final Number value, final Number defaultValue) {

    return value == null ? defaultValue : value;
  }

  /**
   * Returns a string by given value.<br>
   * Fill "0" in the head of given source if given source value length below
   * given size; string of given source value otherwise.<br>
   * <p>
   * e.g:<br>
   * source:123,size:4 ==> "0123".<br>
   * source:12345,size:4 ==> "12345".
   * </p>
   *
   * @param source the source value.
   * @param size the display string size.
   * @return a string value to display given source..
   */
  public static String toString(final long source, final int size) {

    String sourceString = String.valueOf(source);
    if (sourceString.length() >= size) {
      return sourceString;
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      int whiteSpanSize = size - sourceString.length();
      for (int i = 0; i < whiteSpanSize; i++) {
        stringBuilder.append('0');
      }
      stringBuilder.append(sourceString);
      return stringBuilder.toString();
    }
  }

  /**
   * Return true if booleanValue is not null and is true.
   *
   * @param booleanValue boolean value to judge.
   * @return if booleanValue is true.
   */
  public static Boolean getValue(final Boolean booleanValue) {

    return booleanValue != null && booleanValue;
  }

  public static Boolean getValue(final Boolean booleanValue, final Boolean defaultValue) {

    return booleanValue == null ? defaultValue : booleanValue;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static <T extends Enum> T getEnumInstance(final Class<T> ct, final String name) {

    return isBlank(name) ? null : (T) Enum.valueOf(ct, name);
  }

}
