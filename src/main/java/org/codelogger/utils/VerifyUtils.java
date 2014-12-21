package org.codelogger.utils;

import static org.codelogger.utils.StringUtils.isEmpty;

public class VerifyUtils {

  private static final String MOBILE_NUMBER_PATTERN = "^1\\d{10}$";

  private static final String MAIL_PATTERN = "^([a-zA-Z0-9]+[-_\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[-_]?)+[a-zA-Z0-9]+(\\.[a-zA-Z]{2,4})(\\.[a-zA-Z]{2})?$";

  private static final String IP_PATTERN = "(([0-1]?[0-9]?[0-9]|(2[0-4][0-9]|25[0-5]))\\.){3}([0-1]?[0-9]?[0-9]|(2[0-4][0-9]|25[0-5]))";

  /**
   * Return true if given number is a mobile phone number; false otherwise.
   * 
   * @param mobileNumber mobile phone number to be tested.
   * @return true if given number is a mobile phone number; false otherwise.
   */
  public static boolean isMobileNumber(final String mobileNumber) {

    if (isEmpty(mobileNumber)) {
      return false;
    }
    return mobileNumber.matches(MOBILE_NUMBER_PATTERN);
  }

  /**
   * Return true if given number is an email address; false otherwise.
   * 
   * @param mailAddress Email address to be tested.
   * @return true if given number is an email address; false otherwise.
   */
  public static boolean isEmail(final String mailAddress) {

    if (isEmpty(mailAddress)) {
      return false;
    }
    return mailAddress.matches(MAIL_PATTERN);
  }

  /**
   * Return true if given number is a IP address; false otherwise.
   * 
   * @param ipAddress IP address to be tested.
   * @return true if given number is a IP address; false otherwise.
   */
  public static boolean isIPAddress(final String ipAddress) {

    if (isEmpty(ipAddress)) {
      return false;
    }
    return ipAddress.matches(IP_PATTERN);
  }
}
