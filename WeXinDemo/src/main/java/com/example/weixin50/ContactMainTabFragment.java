package com.example.weixin50;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weixin50.databinding.Tab03Binding;
import com.example.weixin50.util.PermissionUtils;
import com.example.weixin50.util.UriUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

/**
 * Created by ldh on 2016/8/31 0031.
 * 图片相关demo
 */
public class ContactMainTabFragment extends Fragment implements View.OnClickListener {
    public static final int REQUEST_CODE_ALBUM = 100;
    private static final int REQUEST_CAMERA = 101;

    private File cacheFile;
    private Activity mActivity;
    Tab03Binding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.tab03, container, false);
        initView();
        mActivity = getActivity();
        return binding.getRoot();
    }

    private void initView() {
        binding.imagePicker.setOnClickListener(this);
        binding.takephoto.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_picker:
                checkPicker();
                break;
            case R.id.takephoto:
                checkCamera();
                break;
            default:
                break;
        }
    }

    public static final int PERMISSION_REQUEST_CAMERA = 10;
    public static final int PERMISSION_REQUEST_STOTAGE = 11;

    public void checkCamera() {
        if (PermissionUtils.checkFragmentPermisssion(getActivity(), this, Manifest.permission.CAMERA,
                getString(R.string.permission_tips, getString(R.string.app_name), getString(R.string.permission_camera)), PERMISSION_REQUEST_CAMERA, false)) {
            gotoCamera();
        }
    }

    private void gotoCamera() {
        Intent i1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cacheFile = new File(getPath());
        i1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cacheFile));
        startActivityForResult(i1, REQUEST_CAMERA);
    }

    private String getPath() {
        return mActivity.getExternalCacheDir() != null ?
                mActivity.getExternalCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() :
                mActivity.getCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis();
    }

    public void checkPicker() {
        if (PermissionUtils.checkFragmentPermisssion(getActivity(), this, Manifest.permission.READ_EXTERNAL_STORAGE,
                getString(R.string.permission_tips, getString(R.string.app_name), getString(R.string.permission_storage)), PERMISSION_REQUEST_STOTAGE, false)) {
            gotoPicture();
        }
    }

    private void gotoPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_ALBUM);
    }

    @Deprecated
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_STOTAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPicker();
                } else {
                    PermissionUtils.createPermissionDialog(getActivity(), getString(R.string.permission_tips, getString(R.string.app_name), getString(R.string.permission_storage)), false);
                }
                break;
            case PERMISSION_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkCamera();
                } else {
                    PermissionUtils.createPermissionDialog(getActivity(), getString(R.string.permission_tips, getString(R.string.app_name), getString(R.string.permission_camera)), false);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    /**
     * 走fragment中的回调，通过activity回调的super方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ALBUM:
                if (resultCode == -1 && null != data) {
                    String path = UriUtils.getRealPathFromURI(getActivity(), data.getData());
                    binding.text.setText(path);
                    if (!TextUtils.isEmpty(path)) {
                        Uri uri = Uri.fromFile(new File(path));
                        binding.image.setImageURI(uri);
                    }
                }
                break;
            case REQUEST_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    String path = "file://" + cacheFile.getAbsolutePath();
                    binding.text.setText(path);
//                    if (!TextUtils.isEmpty(path)) {
//                        Uri uri = Uri.fromFile(new File(path));
//                        image.setImageURI(uri);
//                    }
                    onOk("file://" + cacheFile.getAbsolutePath());
                } else {
                    Toast.makeText(getActivity(), "图片返回失败", Toast.LENGTH_LONG).show();
                }
            default:
                break;
        }
    }

    private void onOk(String path) {
        ImageLoader.getInstance().loadImage(path, new ImageSize(500, 500), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String arg0, View arg1) {
            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {

            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap bm) {
                binding.image.setImageBitmap(bm);
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {

            }
        });
    }


}
