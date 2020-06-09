package com.gzy.paymentlistener.http;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RemoteService {

    @FormUrlEncoded
    @POST("/")
    Call<Object> postValue(@FieldMap Map<String, Double> paramsMap);
}