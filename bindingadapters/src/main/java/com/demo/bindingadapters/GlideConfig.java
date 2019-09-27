package com.demo.bindingadapters;

import com.bumptech.glide.request.RequestOptions;

/**
 * desc:
 * Created by ldh on 2019-09-27.
 */
public final class GlideConfig {
    private static RequestOptions mDefaultRequestOptions = newOptions().autoClone();

    private GlideConfig() {
    }

    public static void setDefaultRequestOptions(RequestOptions requestOptions) {
        if (requestOptions == null) {
            throw new NullPointerException("requestOptions can not be null");
        }
        mDefaultRequestOptions = requestOptions.clone().autoClone();
    }

    public static RequestOptions getDefaultRequestOptions() {
        return mDefaultRequestOptions;
    }

    public static RequestOptions newOptions() {
        return new RequestOptions();
    }
}
