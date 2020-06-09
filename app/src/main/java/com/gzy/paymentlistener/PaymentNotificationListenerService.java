package com.gzy.paymentlistener;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.gzy.paymentlistener.http.Network;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (isPaymentMessage(packageName)) {
            double money = parse(text);
            if (money != -1) {
                postValue(money);
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
    }

    /**
     * 通过包名判断是否是支付宝/微信消息
     *
     * @param packageName 包名
     * @return true 支付宝/微信消息
     */
    private boolean isPaymentMessage(String packageName) {
        return !TextUtils.equals("com.eg.android.AlipayGphone", packageName)
                || !TextUtils.equals("com.tencent.mm", packageName);
    }

    private static double parse(String string) {
        String regEx = "[1-9]\\d*|0\\.\\d\\d元";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        if (m.find()) {
            String s = m.group(0);
            s = s.substring(4, s.length() - 1);
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

    private void postValue(double money) {
        Map<String, Double> param = new HashMap<>();
        param.put(Global.paramName, money);
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