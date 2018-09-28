package com.ldh.android.rxhttpcore.model;

/**
 * Created by ldh on 2018/4/26.
 */
public interface BaseEntity<T> {
    boolean isStatusOk();

    T getData();

    String getResponseCode();

    String getMessage();
}
