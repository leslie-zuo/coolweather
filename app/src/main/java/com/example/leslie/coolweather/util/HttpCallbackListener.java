package com.example.leslie.coolweather.util;

/**
 * Created by leslie on 2016/7/23.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
