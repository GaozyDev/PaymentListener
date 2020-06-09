package com.gzy.paymentlistener.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemoteService {

    @POST("/")
    Call<Object> postValue(@Body RequestBean requestBean);
}