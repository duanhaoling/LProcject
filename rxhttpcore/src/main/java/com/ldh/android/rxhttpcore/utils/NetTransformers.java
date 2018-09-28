package com.ldh.android.rxhttpcore.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ldh.android.rxhttpcore.exception.ErrorInfo;
import com.ldh.android.rxhttpcore.exception.ExceptionEngine;
import com.ldh.android.rxhttpcore.exception.ServerException;
import com.ldh.android.rxhttpcore.model.HttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ldh on 2018/4/26.
 */
public class NetTransformers {

    /**
     * 判断返回码，抛出异常或者取出数据 ，并交给ExceptionEngine处理异常
     * map()  判断返回状态码，取出data，保证不为空。
     * onErrorResumeNext()  通过ExceptionEngine对网络异常和服务端返回异常分类，统一为ErrorInfo
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> dataTransformer() {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {
                return upstream.map(new Function<HttpResult<T>, T>() {
                    @Override
                    public T apply(HttpResult<T> httpResult) throws Exception {
                        if (httpResult == null) {
                            throw new ServerException(ErrorInfo.CODE_RESPONSE_EMPTY, "服务器无数据返回");
                        }
                        if (!httpResult.isStatusOk()) {
                            throw new ServerException(httpResult.getResponseCode(), httpResult.getMessage());
                        }
                        if (httpResult.getData() == null) {
                            throw new ServerException(ErrorInfo.CODE_DATA_EMPTY, TextUtils.isEmpty(httpResult.getMessage()) ? "部分数据为空" : httpResult.getMessage());
                        }
                        return httpResult.getData();
                    }
                }).onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };

    }

    /**
     * 交给ExceptionEngine处理异常
     *
     * @param <T>
     */
    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {

        @Override
        public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
            //ExceptionEngine为处理异常的驱动器
            return Observable.error(ExceptionEngine.handleException(throwable));
        }
    }


    /**
     * 线程调度
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
