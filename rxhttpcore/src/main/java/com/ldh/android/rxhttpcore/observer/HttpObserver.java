package com.ldh.android.rxhttpcore.observer;

import androidx.annotation.NonNull;
import android.util.Log;

import com.ldh.android.rxhttpcore.exception.ErrorInfo;
import com.ldh.android.rxhttpcore.utils.UriUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 适用于标准类型(HttpResult<T>)的返回数据，不会自动取消回调
 * <p>
 * <p>
 * Created by ldh on 2017/8/15.
 */

public abstract class HttpObserver<T> implements Observer<T> {
    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }


    @Override
    public void onError(Throwable e) {
        Log.e(UriUtils.TAG, "HttpObserver.onError:" + e.toString());
        e.printStackTrace();

        if (e instanceof ErrorInfo) {
            ErrorInfo ex = (ErrorInfo) e;
//            if (ERRCODE_TOKEN_INVALID.equals(ex.getCode())
//                    && AnlicationHelper.getContext() instanceof Authable) {
//                Authable authable = (Authable) AnjukeHelper.getContext();
//                authable.onAuthTokenFailed(ex.getErrorMsg());
//                return;
//            }
            onErrorInfo(ex);
        } else {
            onErrorInfo(new ErrorInfo(e, ErrorInfo.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 取消回调
     */
    public void unsubscribe() {
        if (!disposable.isDisposed())
            disposable.dispose();
    }

    public abstract void onErrorInfo(@NonNull ErrorInfo ef);

}
