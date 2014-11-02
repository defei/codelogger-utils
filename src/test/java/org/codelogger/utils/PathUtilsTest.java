package org.codelogger.utils;

import org.codelogger.utils.PathUtils;
import org.junit.Ignore;
import org.junit.Test;

public class PathUtilsTest {

    @Test
    @Ignore
    public void getCurrentClassPath_correctParameter_() {

        String currentClassPath = PathUtils.getWebProjectPath(this);
        System.out.println(currentClassPath);
    }

}
