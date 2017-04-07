package com.kazekim.techcrunch.utils;

import android.util.Log;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class JHLog {
    public static final boolean isEnableLogParser = true;

    public static void logParser(Class c, String message) {
        if (isEnableLogParser) {
            Log.d("Parser " + "[" + c.getSimpleName() + "]", message);
        }
    }

    public static void logParserE(Class c, String message)
    {
        if(isEnableLogParser){
            Log.e("Parser "+"["+c.getSimpleName()+"]", message);
        }
    }

}
