package com.ldh.android.rxhttpcore.exception;

import android.net.ParseException;
import android.util.Log;

import com.alibaba.fastjson.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by ldh on 2017/8/14.
 */

public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ErrorInfo handleException(Throwable e) {
        ErrorInfo ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ErrorInfo(e, ErrorInfo.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setErrorMsg("网络错误");  //均视为网络错误
                    break;
            }
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ErrorInfo(resultException, resultException.code);
            ex.setErrorMsg(resultException.message);
        } else if (e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ErrorInfo(e, ErrorInfo.PARSE_ERROR);
            ex.setErrorMsg("解析错误");            //均视为解析错误
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            ex = new ErrorInfo(e, ErrorInfo.NETWORD_ERROR);
            ex.setErrorMsg("连接失败");  //均视为网络错误
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ErrorInfo(e, ErrorInfo.SSL_ERROR);
            ex.setErrorMsg("证书验证失败");
        } else if (e instanceof SocketTimeoutException) {
            ex = new ErrorInfo(e, ErrorInfo.TIMEOUT_ERROR);
            ex.setErrorMsg("连接超时");
        } else {
            ex = new ErrorInfo(e, ErrorInfo.UNKNOWN);
            ex.setErrorMsg("未知错误");          //未知错误
        }
        return ex;
    }

    public static ErrorInfo handleExceptionOld(Throwable e) {
        ErrorInfo info = new ErrorInfo();
        if (e instanceof HttpException) {
            HttpException he = (HttpException) e;
            info.setCode(he.code() + "");
            info.setErrorMsg("网络错误");
        } else if (e instanceof JSONException || e instanceof ParseException) {
            info.setCode(ErrorInfo.PARSE_ERROR);
            info.setErrorMsg("解析错误");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            info.setCode(ErrorInfo.NETWORD_ERROR);
            info.setErrorMsg("连接失败");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            info.setCode(ErrorInfo.SSL_ERROR);
            info.setErrorMsg("证书验证失败");
        } else if (e instanceof SocketTimeoutException) {
            info.setCode(ErrorInfo.TIMEOUT_ERROR);
            info.setErrorMsg("连接超时");
        } else {
            Log.e("OkHttp", Log.getStackTraceString(e));
            info.setCode(ErrorInfo.UNKNOWN);
//            info.setErrorMsg("未知错误: " + (e == null ? "null" : e.getMessage()));
            info.setErrorMsg("未知错误"); //否则toast提示会有问题
        }
        return info;
    }
}
