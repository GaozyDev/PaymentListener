package com.gzy.paymentlistener.http;

import com.gzy.paymentlistener.Factory;
import com.gzy.paymentlistener.Global;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder();
                    // todo Content-Type
//                    builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
//                    builder.addHeader("Content-Type", "application/text");
                    builder.addHeader("Content-Type", "application/json");
                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                })
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder().baseUrl(Global.url)
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
