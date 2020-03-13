package com.lingyun.weibo.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lingyun.weibo.base.BaseBean;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.classes.oauth2.model.WBAuthModel;
import com.lingyun.weibo.constant.Constants;
import com.lingyun.weibo.utils.LogUtil;
import com.lingyun.weibo.utils.ToastUtil;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeiBoHttp {

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request request;
                    HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                            // Provide your custom parameter here
//                            .addQueryParameter("token", UserHelper.getUser().getToken())
                            .build();
                    request = originalRequest.newBuilder().url(modifiedUrl).build();
                    return chain.proceed(request);
                }
            })
            .build();
   private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit mRetrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(WeiBoApi.baseUrl)//基础URL 建议以 / 结尾
            .addConverterFactory(GsonConverterFactory.create(gson))//设置 Json 转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
            .build();

    public static Observable<WBAuthModel> oauth2AccessToken(String code) {
        Map map = new HashMap();
        map.put("client_id", Constants.APP_KEY);
        map.put("client_secret", Constants.APP_Secret);
        map.put("code", code);
        map.put("redirect_uri", Constants.REDIRECT_URL);
        return mRetrofit.create(WeiBoApi.class)
                .oauth2AccessToken(map)
                .subscribeOn(Schedulers.io())
                .compose(ResponseTransformer.handleResult())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
