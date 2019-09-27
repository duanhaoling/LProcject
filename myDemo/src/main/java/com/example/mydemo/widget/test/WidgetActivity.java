package com.example.mydemo.widget.test;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydemo.R;
import com.example.mydemo.widget.viewpagerfragment.WidgetViewPagerFragment;

public  class WidgetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.fl_act_widget, new WidgetViewPagerFragment()).commit();
    }

}
