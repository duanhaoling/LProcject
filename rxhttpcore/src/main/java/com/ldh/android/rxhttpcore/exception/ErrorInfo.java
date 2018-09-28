package com.ldh.android.rxhttpcore.exception;

/**
 * Created by ldh on 16/8/3.
 */
public class ErrorInfo extends Throwable {
    /**
     * http请求返回为空
     */
    public final static String CODE_RESPONSE_EMPTY = "-666666";

    /**
     * data字段为空时的code码
     */
    public final static String CODE_DATA_EMPTY = "-666667";
    /**
     * 未知错误
     */
    public static final String UNKNOWN = "1000";
    /**
     * 解析错误
     */
    public static final String PARSE_ERROR = "1001";
    /**
     * 网络错误
     */
    public static final String NETWORD_ERROR = "1002";
    /**
     * 协议出错
     */
    public static final String HTTP_ERROR = "1003";
    /**
     * 证书出错
     */
    public static final String SSL_ERROR = "1005";

    /**
     * 连接超时
     */
    public static final String TIMEOUT_ERROR = "1006";


    private String code;
    private String errorMsg; //toast展示的错误信息

    public ErrorInfo() {
    }

    public ErrorInfo(Throwable throwable, String code) {
        super(throwable);
        this.code = code;
    }

    public ErrorInfo(Throwable throwable, String code, String message) {
        super(throwable);
        this.code = code;
        this.errorMsg = message;
    }

    public ErrorInfo(String code, String message) {
        super(new Exception());
        this.code = code;
        this.errorMsg = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean hasCode() {
        return code != null && code.trim().length() > 0;
    }

    public String getErrorMsg() {
        return errorMsg == null ? getMessage() : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isNetworkError() {
        return NETWORD_ERROR.equals(code);
    }


    /**
     * 重写getMessage()方法, 避免误掉而导致错误
     *
     * @return
     */
    @Override
    public String getMessage() {
//        if (errorMsg != null) return errorMsg;
        return super.getMessage();
    }
}
