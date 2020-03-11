package com.lingyun.weibo.http;

import com.lingyun.weibo.classes.home.model.HomeModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeiBoApi {
    public final String baseUrl = "https://api.weibo.com/";


    @GET("2/statuses/home_timeline.json")
    Observable<HomeModel> homeTimeLine(@QueryMap Map<String,Object> map);
}
