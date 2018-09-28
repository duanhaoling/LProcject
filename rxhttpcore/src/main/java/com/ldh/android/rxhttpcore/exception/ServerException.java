package com.ldh.android.rxhttpcore.exception;

/**
 * Created by ldh on 2017/8/15.
 */

public class ServerException extends RuntimeException {
    public String code;
    public String message;

    public ServerException(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }
}
