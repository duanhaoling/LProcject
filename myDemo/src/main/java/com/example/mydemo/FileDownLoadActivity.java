package com.example.mydemo;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.example.mydemo.base.BaseActivity;
import com.example.mydemo.databinding.ActivityFileDownLoadBinding;

public class FileDownLoadActivity extends BaseActivity {

    ActivityFileDownLoadBinding binding;
    String url = "";
    String savePath = "Download/ldh";
    private long downloadId;
    private boolean isDownloading;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_file_down_load);
        initEvents();

    }

    private void initEvents() {
        binding.button1.setOnClickListener(v -> {
            downloadWithDownloadManager();
        });
    }

    private void downloadWithDownloadManager() {
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        String apkUrl = "https://wos.anjukestatic.com/PTNmLkJiPqP/file/154348499983.mp3";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));

//        File savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        String fileName = apkUrl.substring(apkUrl.lastIndexOf("/") + 1);
        binding.textView2.append(apkUrl);
        binding.textView3.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName);

        request.setAllowedOverMetered(true);
        request.setTitle("下载音乐");
        request.setDescription("音乐下载中");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        downloadId = manager.enqueue(request);
        isDownloading = true;

        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        runnable = () -> {
            Cursor cursor = manager.query(query);
            if (cursor.moveToFirst()) {
                long bytesDownload = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                long bytesTotal = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                int progress = (int) (bytesDownload * 100 / bytesTotal);
                binding.progressBar2.setProgress(progress);
                if (progress < 100) {
                    binding.progressBar2.postDelayed(runnable, 200);
                }
            }
            cursor.close();

        };
        binding.progressBar2.postDelayed(runnable, 200);

    }


    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    @NonNull
    public String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }


}
