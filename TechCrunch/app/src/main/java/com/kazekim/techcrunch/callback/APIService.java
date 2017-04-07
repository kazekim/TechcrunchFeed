package com.kazekim.techcrunch.callback;

import com.kazekim.techcrunch.model.ResultDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public interface APIService {

    /**
     * News
     */
    @FormUrlEncoded
    @GET("articles")
    Call<ResultDao> loadNewsList(@Field("source") String source, @Field("sortBy") String sortBy, @Field("apiKey") String apiKey);

}
