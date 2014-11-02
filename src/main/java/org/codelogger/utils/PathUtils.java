package org.codelogger.utils;

import java.net.URL;

public class PathUtils {

    /**
     * The parameter currentObject recommend to user 'this', because of can not
     * be a object which build by manually with 'new';
     * 
     * @param currentObject
     * @return web project physical path.
     */
    public static String getWebProjectPath(Object currentObject) {

        Class<? extends Object> class1 = currentObject.getClass();
        ClassLoader classLoader = class1.getClassLoader();
        URL resource = classLoader.getResource("/");
        String path = resource.getPath();
        int webRootIndex = StringUtils.indexOfIgnoreCase(path, "WEB-INF");
        if (path.indexOf(":/") > 0) {
            return path.substring(1, webRootIndex);
        } else {
            return path.substring(0, webRootIndex);
        }
    }
}
