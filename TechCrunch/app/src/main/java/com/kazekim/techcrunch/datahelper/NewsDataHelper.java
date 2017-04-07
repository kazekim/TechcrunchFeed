package com.kazekim.techcrunch.datahelper;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kazekim.techcrunch.model.NewsDao;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class NewsDataHelper {
    private static NewsDataHelper instance;
    public static NewsDataHelper getInstance() {
        if (instance == null)
            instance = new NewsDataHelper();
        return instance;
    }

    private ArrayList<NewsDao> newsList;

    public NewsDataHelper() {

        newsList = new ArrayList();

        // Load data from Persistent Storage
        loadCache();

    }

    /**
     * News
     */

    public List<NewsDao> getNewsList() {
        return newsList;
    }

    public NewsDao getNewsAtPosition(int index)
    {
        return newsList.get(index);
    }

    public int getNewsCount()
    {
        if(newsList == null)
            return 0;
        return newsList.size();
    }

    public void setNewsList(List<NewsDao> daos) {
        newsList = (ArrayList<NewsDao>) daos;
        saveCache();
    }

    public void replaceNewsList(List<NewsDao> daos)
    {
        newsList = (ArrayList<NewsDao>) daos;
        saveCache();
    }

    public void insertNewsAtTopPosition(NewsDao dao)
    {

        if(newsList == null)
            newsList = new ArrayList();
        newsList.add(0, dao);
        saveCache();
    }

    public void removePurchaseBuyHistoryAtIndex(int index)
    {
        newsList.remove(index);
    }


    /**
     * Data Storage
     */

    public Bundle onSaveInstanceState()
    {
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("newsList", newsList);

        return bundle;
    }

    public void onRestoreInstanceState (Bundle savedInstanceState)
    {
        newsList = savedInstanceState.getParcelableArrayList("newsList");
    }

    public void saveCache()
    {

        String newsListJson = new Gson().toJson(newsList);

        Map<String,String> map =  new HashMap();
        map.put("newsList", newsListJson);

        String json = new Gson().toJson(map);

        TCSharedPreference.getInstance().cacheNews(json);
    }

    private void loadCache()
    {
        String json = TCSharedPreference.getInstance().loadCacheNews();

        if(json == null)
            return;

        try {
            JSONObject obj = new JSONObject(json);

            Type purchaseItemListType = new TypeToken<ArrayList<NewsDao>>(){}.getType();
            newsList = new Gson().fromJson(obj.getString("newsList"),purchaseItemListType );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void destroy()
    {
        saveCache();
        instance = null;
    }
}
