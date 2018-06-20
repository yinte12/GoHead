package com.gohead.core.util;

import java.util.List;

/**
 * @author super me
 * @project_name gohead
 * @date 2017-3-1
 */
public class Common {

    public static boolean isEmpty(List<String> noCacheMethod) {
        if (noCacheMethod == null || noCacheMethod.size() < 1)
            return true;
        return false;
    }

    public static boolean isEmpty(Object[] args) {
        if (args == null || args.length < 1)
            return true;
        return false;
    }

}
