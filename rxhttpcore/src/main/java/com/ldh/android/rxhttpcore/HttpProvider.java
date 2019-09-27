package com.ldh.android.rxhttpcore;

import androidx.annotation.NonNull;
import android.util.Log;

import com.ldh.android.rxhttpcore.model.HttpResult;
import com.ldh.android.rxhttpcore.observer.HttpObserver;
import com.ldh.android.rxhttpcore.utils.NetTransformers;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by ldh on 2018/4/26.
 * 每个请求对象对应一个新的实例
 * <p>
 * T 如果替换为别的网络框架，创建相应的service，更换这里的RetrofitFactory
 * V 需要返回的数据结构
 */
public class HttpProvider<T, V> {
    private T service;
    private Class<T> clazz;
    private ServerPoxy<T, V> poxy;

    /**
     * 使用代理类，执行实际请求，方便链式调用
     *
     * @param <T>
     * @param <V>
     */
    @FunctionalInterface
    public interface ServerPoxy<T, V> {
        Observable<HttpResult<V>> getHttpObservabe(T service);
    }

    private HttpProvider(Class<T> clazz, ServerPoxy<T, V> poxy) {
        this.clazz = clazz;
        this.poxy = poxy;
    }

    /**
     * @param clazz Service接口对应的Class
     * @param poxy  获取数据操作
     * @param <T>   Service接口
     * @param <V>   返回的数据类型
     * @return
     */
    public static <T, V> HttpProvider<T, V> newInstance(Class<T> clazz, ServerPoxy<T, V> poxy) {
        ServerPoxy<T, V> newPoxy = new NewServerPoxy<>(poxy);
        return new HttpProvider<T, V>(clazz, newPoxy);
    }


    /**
     * 添加日志获取请求响应时间
     * @param <T>
     * @param <V>
     */
    public static class NewServerPoxy<T, V> implements ServerPoxy<T, V> {

        ServerPoxy<T, V> poxy;

        NewServerPoxy(ServerPoxy<T, V> poxy) {
            this.poxy = poxy;
        }

        @Override
        public Observable<HttpResult<V>> getHttpObservabe(T service) {
            Log.d("requireNet", " before" + service.toString());
            long before = System.currentTimeMillis();
            Observable<HttpResult<V>> httpObservabe = poxy.getHttpObservabe(service);
            long after = System.currentTimeMillis();
            long continueTime = after - before;
            System.out.println("create Thread = " + Thread.currentThread().getName() + "\n");
            System.out.println(" after " + continueTime + "ws ");
            Log.d("requireNet", " after " + continueTime + "ws " + Thread.currentThread().getName());
            return httpObservabe;
        }
    }

    public HttpProvider<T, V> executeLoadData(HttpObserver<V> observer) {
        if (service == null) {
            service = RetrofitFactory.createService(clazz); //如果没有设置baseUrl，执行默认初始化
        }
        Observable<HttpResult<V>> data = poxy.getHttpObservabe(service);
        createDatas(data, observer);
        return this;
    }

    /**
     * 只接受HttpResult<T>形式的返回数据
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    private <T> void createDatas(Observable<HttpResult<T>> observable, HttpObserver<T> observer) {
        observable
                .compose(NetTransformers.<T>dataTransformer())
                .compose(NetTransformers.<T>switchSchedulers())
                .subscribe(observer);
    }


    /**
     * 自定义baseUrl
     *
     * @param baseUrl
     * @return
     */
    public HttpProvider<T, V> baseUrl(@NonNull String baseUrl) {
        service = RetrofitFactory.createService(clazz, baseUrl);
        return this;
    }

    public Observable<V> getDataObservable() {
        if (service == null) {
            service = RetrofitFactory.createService(clazz); //如果没有设置baseUrl，执行默认初始化
        }
        return poxy.getHttpObservabe(service).map(new Function<HttpResult<V>, V>() {
            @Override
            public V apply(HttpResult<V> vHttpResult) throws Exception {
                return vHttpResult.getData();
            }
        });
    }


}
