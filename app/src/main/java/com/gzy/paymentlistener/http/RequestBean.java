package com.gzy.paymentlistener.http;

public class RequestBean {

    private double money;
    private String key;

    public RequestBean(double money, String key) {
        this.money = money;
        this.key = key;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
