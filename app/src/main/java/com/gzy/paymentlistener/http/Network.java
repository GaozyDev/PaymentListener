package com.gzy.paymentlistener.http;

import com.gzy.paymentlistener.Factory;
import com.gzy.paymentlistener.Global;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 */
public class Network {

    private Network() {
    }

    private static Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder();
        return builder.baseUrl(Global.url)
                .client(client)
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
