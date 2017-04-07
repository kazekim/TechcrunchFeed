package com.kazekim.techcrunch.callback;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kazekim.techcrunch.R;
import com.kazekim.techcrunch.datahelper.Contextor;
import com.kazekim.techcrunch.helper.JHResourceHelper;
import com.kazekim.techcrunch.utils.JHSettings;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class HttpManager {

    private static HttpManager instance;

    public enum JHMediaType {
        MultiPartFormData("multipart/form-data"),
        TextPlain("text/plain"),
        Image("image/*"),
        ImageJpeg("image/jpeg");


        private final String actionString;

        JHMediaType(String statusString)
        {
            this.actionString = statusString;
        }

        public String toString()
        {
            return actionString;
        }

    }

    // Base URL : http://188.166.233.206/services

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private APIService service;

    private HttpManager() {

        mContext = Contextor.getInstance().getContext();

        Gson gson =
                new GsonBuilder()
                        .serializeNulls()
                        .setDateFormat("yyy-MM-dd'T'HH:mm:ssZ")
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JHSettings.apiUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(APIService.class);
    }

    public HttpManager(Object objectAdaptor){

        init(objectAdaptor);
    }

    public HttpManager(Object objectAdaptor, boolean isTest) {
        if(isTest) {
            mContext = Contextor.getInstance().getContext();

            Gson gson = initGson(objectAdaptor);

            Retrofit retrofit = initRetrofit("http://requestb.in/", gson);

            service = retrofit.create(APIService.class);
        }else{
            init(objectAdaptor);
        }
    }

    private void init(Object objectAdaptor)
    {
        mContext = Contextor.getInstance().getContext();

        Gson gson = initGson(objectAdaptor);

        Retrofit retrofit = initRetrofit(JHSettings.apiUrl, gson);

        service = retrofit.create(APIService.class);
    }

    private Gson initGson(Object objectAdaptor)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(APIService.class,objectAdaptor)
                .setDateFormat("yyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .create();
        return gson;
    }

    private Retrofit initRetrofit(String url,Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }


    public APIService getService()
    {
        return service;
    }
}

