package com.ldh.android.rxhttpcore;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ldh.android.rxhttpcore.fastjson.FastJsonConverterFactory;
import com.ldh.android.rxhttpcore.interceptor.NetInterceptor;
import com.ldh.android.rxhttpcore.model.HttpResult;
import com.ldh.android.rxhttpcore.observer.HttpObserver;
import com.ldh.android.rxhttpcore.utils.NetTransformers;
import com.ldh.android.rxhttpcore.utils.UriUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by ldh on 2018/4/26.
 */
public final class RetrofitFactory {

    private static OkHttpClient client;
    private static final int DEFAULT_TIMEOUT = 20;
    private static Retrofit retrofitV3; //可复用

    private RetrofitFactory() {

    }

    public static <T> T createService(final Class<T> service) {
        if (retrofitV3 == null) {
            String host = UriUtils.baseUrl;
            Interceptor interceptor = new NetInterceptor();
            String baseUrl = host.endsWith("/") ? host : host + "/";//retrofit2: baseUrl must end in /
            retrofitV3 = retrofitAdapter(baseUrl, interceptor);
        }
        return retrofitV3.create(service);
    }

    public static <T> T createService(final Class<T> service, String baseUrl) {
        if (!baseUrl.endsWith("/")) baseUrl = baseUrl + "/";//retrofit2: baseUrl must end in /
        Retrofit retrofit = retrofitAdapter(baseUrl, null);
        return retrofit.create(service);
    }

    private static Retrofit retrofitAdapter(String url, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, DEFAULT_TIMEOUT, TimeUnit.SECONDS));

        if (interceptor != null) builder.addInterceptor(interceptor);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor())
                    .addNetworkInterceptor(new StethoInterceptor());
        }

        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    /**
     * 只接受HttpResult<T>形式的返回数据
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void createDatas(Observable<HttpResult<T>> observable, HttpObserver<T> observer) {
        observable
                .compose(NetTransformers.<T>dataTransformer())
                .compose(NetTransformers.<T>switchSchedulers())
                .subscribe(observer);
    }



}
