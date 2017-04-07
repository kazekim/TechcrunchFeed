package com.kazekim.techcrunch.template.callback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public abstract class JHCallBack<T> implements Callback<T> {

    private static List<JHCallBack> mList = new ArrayList<>();

    private boolean isCanceled = false;
    private Object mTag = null;

    public static void cancelAll() {
        Iterator<JHCallBack> iterator = mList.iterator();
        while (iterator.hasNext()){
            iterator.next().isCanceled = true;
            iterator.remove();
        }
    }

    public static void cancel(Object tag) {
        if (tag != null) {
            Iterator<JHCallBack> iterator = mList.iterator();
            JHCallBack item;
            while (iterator.hasNext()) {
                item = iterator.next();
                if (tag.equals(item.mTag)) {
                    item.isCanceled = true;
                    iterator.remove();
                }
            }
        }
    }

    public JHCallBack() {
        mList.add(this);
    }

    public JHCallBack(Object tag) {
        mTag = tag;
        mList.add(this);
    }

    public void cancel() {
        isCanceled = true;
        mList.remove(this);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if(!isCanceled){
            T result = response.body();
            onSuccess(response, result);
        }
        mList.remove(this);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!isCanceled)
            onFailure(t);
        mList.remove(this);
    }

    public abstract void onSuccess(Response response, T t);

    public abstract void onFailure(Throwable t);
}
