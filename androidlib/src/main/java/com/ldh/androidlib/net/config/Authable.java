package com.ldh.androidlib.net.config;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

/**
 * Created by ldh on 2017/8/16.
 */

public interface Authable {
    /**
     * 返回用户id
     */
    String id();

    /**
     * 返回token值
     *
     * @return
     */
    @WorkerThread
    String token();

    /**
     * token失效室的回调
     */
    @UiThread
    void onAuthTokenFailed(String msg);
}
