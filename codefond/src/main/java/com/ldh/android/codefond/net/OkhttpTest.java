package com.ldh.android.codefond.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * desc:
 * Created by ldh on 2019/4/22.
 */
public class OkhttpTest {

    public static void main(String[] args) {
        try {
            okHttpGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void okHttpGet() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com")
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code() + "\n"
                + response.toString() + "\n"
                + response.body().string());
    }





}
