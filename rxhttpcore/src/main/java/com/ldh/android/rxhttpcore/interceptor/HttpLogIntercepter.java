package com.ldh.android.rxhttpcore.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ldh on 2018/4/26.
 */
public class HttpLogIntercepter implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
