package com.gzy.paymentlistener.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemoteService {

    // todo URL
    @POST("/notify/Alipay/MakeQRCode_Notify.aspx")
    Call<Object> postValue(@Body RequestBean requestBean);
}