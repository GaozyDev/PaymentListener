package com.gzy.paymentlistener.http;

import android.text.TextUtils;

import com.gzy.paymentlistener.Global;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求地址重定向
 */
public class RedirectInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        if (TextUtils.isEmpty(Global.url)) {
            return chain.proceed(chain.request());
        }

        HttpUrl httpUrl = HttpUrl.parse(Global.url);
        Request request = chain.request().newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}

