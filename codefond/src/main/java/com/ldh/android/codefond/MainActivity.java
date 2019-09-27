package com.ldh.android.codefond;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.ldh.android.rxhttpcore.HttpProvider;
import com.ldh.android.rxhttpcore.exception.ErrorInfo;
import com.ldh.android.rxhttpcore.model.GankDataService;
import com.ldh.android.rxhttpcore.model.Welfare;
import com.ldh.android.rxhttpcore.observer.HttpObserver;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    testApi();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void testApi() {
        HttpProvider.newInstance(GankDataService.class, service -> service.fetchHistoryTodayInfo(1, 10))
                .executeLoadData(new HttpObserver<List<Welfare>>() {
                    @Override
                    public void onErrorInfo(@NonNull ErrorInfo ef) {
                        mTextMessage.setText(ef.getMessage());
                    }

                    @Override
                    public void onNext(List<Welfare> welfares) {
                        mTextMessage.setText(JSONObject.toJSONString(welfares));
                    }
                });
    }

}
