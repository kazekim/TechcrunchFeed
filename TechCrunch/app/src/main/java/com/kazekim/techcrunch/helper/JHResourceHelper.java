package com.kazekim.techcrunch.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.kazekim.techcrunch.datahelper.Contextor;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class JHResourceHelper {

    public static int getColor(Context context, int colorResId)
    {
        return ContextCompat.getColor(context, colorResId);
    }

    public static Drawable getDrawable(int drawableResId)
    {
        return ContextCompat.getDrawable(Contextor.getInstance().getContext(), drawableResId);
    }

    public static int getDimension(Context context, int dimenResId)
    {
        int sp = (int) ((double)context.getResources().getDimension(dimenResId));
        return sp;
    }

    public static int getInteger(Context context, int integerResId)
    {
        int integer = context.getResources().getInteger(integerResId);
        return integer;
    }

    public static String getText(Context context, int textResId)
    {
        String text = context.getResources().getString(textResId);
        return text;
    }
}
