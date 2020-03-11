package com.lingyun.weibo.http;

import android.content.Context;

import com.lingyun.weibo.base.BaseBean;
import com.lingyun.weibo.classes.home.model.HomeModel;
import com.lingyun.weibo.utils.LogUtil;
import com.lingyun.weibo.utils.ToastUtil;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
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
    private static Retrofit mRetrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(WeiBoApi.baseUrl)//基础URL 建议以 / 结尾
            .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
            .build();


    public static Observable<HomeModel> userAttentionList(Map map) {
        return mRetrofit.create(WeiBoApi.class)
                .homeTimeLine(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<HomeModel, ObservableSource<HomeModel>>() {
                    @Override
                    public ObservableSource<HomeModel> apply(HomeModel infoBeanBaseBean) throws Exception {
                        LogUtil.e(infoBeanBaseBean.toString());
                        return flatResponse(infoBeanBaseBean);
                    }
                });
    }


    static <T> Observable<T> flatResponse(final T response) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                if (response != null) {
                    LogUtil.e(e.serialize().toString());
                } else {
                    e.onError(new NoDataException());
                }
                e.onComplete();
            }
        });
    }


    public static String checkException(Context context, Throwable e) {
        String msg;
        if (e instanceof UnknownHostException) {
            msg = "暂无网络";
        } else if (e instanceof NoDataException) {
            msg = "暂无数据";
        } else if (e instanceof SocketTimeoutException) {
            msg = "连接异常";
        } else if (e instanceof ServerException) {
            msg = "服务异常";
        } else {
            msg = "未知异常";
        }
            ToastUtil.show(context, msg);
        return msg;
    }

    public static class NoDataException extends Throwable {

    }

    private static class ServerException extends Throwable {
        private ServerException(String message) {
            super(message);
        }
    }

}
