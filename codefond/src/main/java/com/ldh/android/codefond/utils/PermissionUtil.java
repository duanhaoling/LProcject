package com.ldh.android.codefond.utils;

import android.app.Activity;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import java.util.ArrayList;

public class PermissionUtil {
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode, PermissionCallBack callBack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> deniedPermissions = null;
            ArrayList<String> deniedAppOpPermissions = null;
            for (String permission : permissions) {
                int result = PermissionChecker.checkSelfPermission(activity, permission);
                switch (result) {
                    case PermissionChecker.PERMISSION_GRANTED:
                        break;
                    case PermissionChecker.PERMISSION_DENIED:
                        if (deniedPermissions == null) {
                            deniedPermissions = new ArrayList<>();
                        }
                        deniedPermissions.add(permission);
                        break;
                    case PermissionChecker.PERMISSION_DENIED_APP_OP:
                        if (deniedAppOpPermissions == null) {
                            deniedAppOpPermissions = new ArrayList<>();
                        }
                        deniedAppOpPermissions.add(permission);
                        break;
                }
            }
            if (deniedAppOpPermissions == null && deniedPermissions == null) {
                if (callBack != null) {
                    callBack.onCheckedPermission(true);
                }
            } else {
                if (deniedPermissions != null) {
                    //TODO 如果是系统弹窗勾选了不再提示，需要主动给出提示。
                    ActivityCompat.requestPermissions(activity, deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
                } else {
                    //TODO 提示具体没有哪个权限
                    if (callBack != null) {
                        callBack.onCheckedPermission(false);
                    }
                }
            }
        } else {
            if (callBack != null) {
                callBack.onCheckedPermission(true);
            }
        }
    }

    public interface PermissionCallBack {
        void onCheckedPermission(boolean isGranted);
    }
}
