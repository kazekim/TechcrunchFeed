package com.kazekim.techcrunch.utils;

import android.widget.Toast;

import com.kazekim.techcrunch.datahelper.Contextor;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/8/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class JHToast {
    public static void show (String text)
    {
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }

    public static void show (int messageResId)
    {
        Toast.makeText(Contextor.getInstance().getContext(),
                messageResId,
                Toast.LENGTH_SHORT)
                .show();
    }
}
