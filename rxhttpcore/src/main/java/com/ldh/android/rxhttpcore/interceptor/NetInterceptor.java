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

    public NetInterceptor(String host, String version) {
        this.headerData = new ThreadLocal<>();
        this.host = host;
        this.version = version;
    }

    public NetInterceptor() {
        this("www.baidu.com", "");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
