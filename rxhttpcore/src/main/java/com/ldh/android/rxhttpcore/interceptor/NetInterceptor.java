package com.ldh.android.rxhttpcore.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ldh on 2018/4/26.
 * 在这里添加header与公共参数
 */
public class NetInterceptor implements Interceptor {

    private ThreadLocal<Map<String, String>> headerData;

    private String host;
    private String version;


    /**
     * 不提供带host与version的构造方法，可以通过解析去获取，方便使用单一OkhttpClient
     */
    public NetInterceptor() {
        this.headerData = new ThreadLocal<>();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
