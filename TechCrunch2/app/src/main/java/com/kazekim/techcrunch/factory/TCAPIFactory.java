package com.kazekim.techcrunch.factory;

import com.kazekim.techcrunch.callback.NewsCallBack;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class TCAPIFactory {

    public static void newsLoadList(NewsCallBack.NewsListCallBackListener listener)
    {
        new NewsCallBack().loadNewsList(listener);
    }
}
