package com.kazekim.techcrunch.callback;

import com.kazekim.techcrunch.model.ResultDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public interface APIService {

    /**
     * News
     */

    @GET("articles")
    Call<ResultDao> loadNewsList(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

}
