package com.ldh.android.codefond.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * desc:
 * Created by ldh on 2019/4/22.
 */
public interface GitHubService {

    String API_URL = "https://api.github.com";

    @GET("/users/{user}")
    Call<UserInfo> getUserInfo(@Path("user") String user);


    @GET("/users/{user}/repos")
    Call<List<ReposInfo>> getUserI(@Path("user") String user,
                                   @Query("page") String page,
                                   @Query("per_page") String per_page);
}
