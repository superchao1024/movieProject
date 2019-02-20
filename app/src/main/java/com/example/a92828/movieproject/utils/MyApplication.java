package com.example.a92828.movieproject.utils;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 整个软件
 */

public class MyApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        //初始化okhttpUtils
        initOkhttpClient();


    }

    private void initOkhttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
}