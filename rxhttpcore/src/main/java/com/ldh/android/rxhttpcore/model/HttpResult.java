package com.ldh.android.rxhttpcore.model;

/**
 * Created by ldh on 2018/4/26.
 */
public class HttpResult<T> implements BaseEntity<T> {

    public String code;
    public String msg;
    public T results;
    public boolean hasmore;
    public boolean error;

    public boolean isSuccess() {
        return !error;
    }

    public boolean hasMore() {
        return hasmore;
    }

    @Override
    public boolean isStatusOk() {
        return isSuccess();
    }

    @Override
    public T getData() {
        return results;
    }

    @Override
    public String getResponseCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
