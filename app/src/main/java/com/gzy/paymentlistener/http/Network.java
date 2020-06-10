package com.gzy.paymentlistener.http;

import com.gzy.paymentlistener.Factory;
import com.gzy.paymentlistener.Global;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 */
public class Network {

    private Network() {
    }

    private static Retrofit getRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return new Retrofit.Builder().baseUrl(Global.url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }

    /**
     * 返回一个请求代理
     *
     * @return RemoteService
     */
    public static RemoteService remote() {
        return Network.getRetrofit().create(RemoteService.class);
    }
}
