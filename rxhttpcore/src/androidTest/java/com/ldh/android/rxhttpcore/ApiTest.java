package com.ldh.android.rxhttpcore;

import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;

import com.ldh.android.rxhttpcore.exception.ErrorInfo;
import com.ldh.android.rxhttpcore.model.GankDataService;
import com.ldh.android.rxhttpcore.model.Welfare;
import com.ldh.android.rxhttpcore.observer.HttpObserver;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ldh on 2018/4/27.
 */
@RunWith(AndroidJUnit4.class)
public class ApiTest {
    @Test
    public void testGankApi() {
        HttpProvider.newInstance(GankDataService.class, service -> service.fetchHistoryTodayInfo(1, 10))
                .executeLoadData(new HttpObserver<List<Welfare>>() {
                    @Override
                    public void onErrorInfo(@NonNull ErrorInfo ef) {
                        assertEquals(ef.getErrorMsg(), "知错误");
                    }

                    @Override
                    public void onNext(List<Welfare> welfare) {

                    }
                });
    }
}
