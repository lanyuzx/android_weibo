package com.lingyun.weibo.http;

import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.classes.oauth2.model.WBAuthModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface WeiBoApi {
    public final String baseUrl = "https://api.weibo.com/";
    //请求授权的token值
    @POST("oauth2/access_token")
    Observable<Response<WBAuthModel>> oauth2AccessToken(@QueryMap Map<String,String> map);

    @GET("2/statuses/home_timeline.json")
    Observable<Response<HomeModel>> homeTimeLine(@QueryMap Map<String,Object> map);
}
