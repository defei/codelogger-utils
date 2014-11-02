package org.codelogger.utils;

public class HtmlUtils {

    public static String noHTML(final String htmlString, final int length) {

        if (htmlString == null || htmlString.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = htmlString.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        int len = str.length();
        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
            str += "......";
        }
        return str;
    }
}
