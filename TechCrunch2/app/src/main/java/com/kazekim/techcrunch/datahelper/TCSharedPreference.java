package com.kazekim.techcrunch.datahelper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class TCSharedPreference {
    private static final String PREFERENCE_NAME = "TCrunch";
    private static TCSharedPreference instance;

    private SharedPreferences prefs;

    public static TCSharedPreference getInstance() {
        if (instance == null)
            instance = new TCSharedPreference();
        return instance;
    }

    public TCSharedPreference()
    {
        this.prefs = Contextor.getInstance().getContext().getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

    }

    private SharedPreferences.Editor openEditor()
    {
        SharedPreferences.Editor editor = prefs.edit();
        return editor;
    }

    private void applyEditor(SharedPreferences.Editor editor)
    {
        editor.apply();
        editor.commit();
    }

    /**
     * News Cache
     */
    public void cacheNews(String json)
    {
        SharedPreferences.Editor editor = openEditor();

        editor.putString("news", json);

        applyEditor(editor);
    }

    public String loadCacheNews()
    {
        String json = prefs.getString("news", null);
        return json;
    }

    public void clearCacheNews()
    {
        SharedPreferences.Editor editor = openEditor();

        editor.putString("news", null);

        applyEditor(editor);
    }
}
