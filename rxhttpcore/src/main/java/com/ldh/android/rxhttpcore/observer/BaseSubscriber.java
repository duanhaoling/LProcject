package com.ldh.android.rxhttpcore.observer;

import androidx.annotation.NonNull;
import android.util.Log;

import com.ldh.android.rxhttpcore.exception.ErrorInfo;
import com.ldh.android.rxhttpcore.exception.ExceptionEngine;
import com.ldh.android.rxhttpcore.utils.UriUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by ldh on 2016/10/11 0011.
 * <p>
 * 通用Observer，不处理服务端数据异常，可用于非HttpResult<T>数据结构，以及返回类型不定的数据结构
 */
public abstract class BaseSubscriber<T> implements Observer<T> {

    private Disposable disposable;

    public BaseSubscriber() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onComplete() {
//        DevUtil.e(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.e(UriUtils.TAG, "BaseSubscriber.onError:" + e.toString());
//        DevUtil.e(TAG, Log.getStackTraceString(e));
        onError(ExceptionEngine.handleExceptionOld(e));
    }


    /**
     * 取消回调
     */
    public void unsubscribe() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public abstract void onError(@NonNull ErrorInfo info);

}
