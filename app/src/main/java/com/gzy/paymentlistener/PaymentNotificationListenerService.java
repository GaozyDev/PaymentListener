package com.gzy.paymentlistener;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.gzy.paymentlistener.http.Network;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentNotificationListenerService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String packageName = sbn.getPackageName();
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);

        if (!TextUtils.equals("com.tencent.mobileqq", packageName)) {
            return;
        }

        Log.e("gaozy", "Notification posted " + packageName + " & " + title + " & " + text);
        postValue(-1);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String packageName = sbn.getPackageName();
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);

        if (!TextUtils.equals("com.tencent.mobileqq", packageName)) {
            return;
        }
        Log.e("gaozy", "Notification removed " + packageName + " & " + title + " & " + text);
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.e("gaozy", "onListenerConnected");
    }

    private double parsing(String text){
        return 0;
    }

    private void postValue(double amount) {
        Map<String, Double> param = new HashMap<>();
        param.put(Global.paramName, amount);
        Network.remote().postValue(param).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {

            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {

            }
        });
    }
}