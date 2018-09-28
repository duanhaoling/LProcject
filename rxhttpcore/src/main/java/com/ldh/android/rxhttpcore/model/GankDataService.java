package com.ldh.android.rxhttpcore.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ldh on 2018/4/26.
 * <p>
 * http://gank.io/api
 */
public interface GankDataService {

    String BASEURL = "http://gank.io/api";

    /**
     * 福利
     *
     * @return
     */
    @GET("data/福利/{pageSize}/{pageIndex}")
    Observable<HttpResult<List<Welfare>>> fetchHistoryTodayInfo(@Path("pageIndex") int pageIndex,
                                                                @Path("pageSize") int pageSize);

}
