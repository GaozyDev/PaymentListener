package com.gzy.paymentlistener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Factory {

    private static final Factory instance;
    private final Gson gson;

    static {
        instance = new Factory();
    }

    public static Gson getGson() {
        return instance.gson;
    }

    private Factory() {
        gson = new GsonBuilder()
                // 设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
    }
}
