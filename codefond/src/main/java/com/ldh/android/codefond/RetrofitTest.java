package com.ldh.android.codefond;

import com.alibaba.fastjson.JSON;
import com.ldh.android.rxhttpcore.fastjson.FastJsonConverterFactory;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * desc:
 * Created by ldh on 2019/4/22.
 */
public class RetrofitTest {
    public static void main(String[] args) {
        try {
            retrfitTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void retrfitTest() throws IOException {
        //创建一个非常简单的指向GitHub API的REST适配器。
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        //创建我们的GitHub API接口的一个实例。
        GitHub github = retrofit.create(GitHub.class);
        //创建一个调用实例来查找Retrofit贡献者。
        Call<List<Contributor>> call = github.contributors("square", "retrofit");
        //获取并打印库的贡献者列表。
        Response response = call.execute();
        System.out.println(response.code() + "\n"
                + response.toString() + "\n"
                + JSON.toJSONString(response.body()));
    }


    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }


    //https://api.github.com/repos/square/retrofit/contributors
    public static final String API_URL = "https://api.github.com";

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }
}
