package com.example.mydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mydemo.dummy.DummyContent;
import com.example.mydemo.mobile.PackageManagerActivity;
import com.example.mydemo.view.DialogActivity;
import com.example.mydemo.view.recycledemo.RecyclerDemoActivity;
import com.ldh.androidlib.adaptive.FileProvider7;
import com.ldh.androidlib.base.BaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener ,ItemFragment.OnListFragmentInteractionListener {


    private static final int REQUEST_CODE_TAKE_PHOTO = 0x100;
    private String mCurrentPhotoPath;
    private List<String> titles;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //初始化toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //初始化悬浮按钮
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //初始化侧边栏
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViewpager();
    }

    private void initViewpager() {

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tableLayout = findViewById(R.id.tab_layout);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        titles.add("热门");
        titles.add("经典");
        titles.add("初级");
        fragments.add(BlankFragment.newInstance());
        fragments.add(MyListFragment.newInstance());
        fragments.add(ItemFragment.newInstance(8));
        FragmentAdapter fadatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(fadatper);
        viewPager.setOffscreenPageLimit(2);
        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            gotoActivity(MediaCenterActivity.class);
        }

        if (id == R.id.nav_share) {
            showDialog();
        }

        if (id == R.id.nav_send) {
            gotoActivity(FileDownLoadActivity.class);

        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //todo 需要申请权限
            takePhotoNoCompress();
        } else if (id == R.id.nav_gallery) {
            gotoActivity(PackageManagerActivity.class);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            gotoActivity(RecyclerDemoActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 测试7.0适配 FileProvider
     */
    private void takePhotoNoCompress() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".png";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();

//            Uri fileUri = Uri.fromFile(file);
            Uri fileUri = FileProvider7.getUriForFile(this, file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
//            Toast.makeText(this, data , Toast.LENGTH_LONG);
        }
    }

    private void showDialog() {
        gotoActivity(DialogActivity.class);

        //这里不可以使用匿名内部类public static
//        CommonDialogFragment.createBuilder(this, getSupportFragmentManager())
//                .setTitle("hello")
//                .setMessage("hello world")
//                .setPositiveButton("yes", (dialog, which) -> {
//                    Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
//                })
//                .setNegativeButton("no", null)
//                .show("test");

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        
    }
}
