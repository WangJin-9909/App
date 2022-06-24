package com.example.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

public class AppInfoUtil {
    private static Context context;

    public static void setContext(Context context) {
        AppInfoUtil.context = context;
    }

    public static int getAppVersionCode() {
        PackageManager packageManager = context.getPackageManager();
        int versionCode = 0;

        try {
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException var4) {
            var4.printStackTrace();
        }

        return versionCode;
    }

    public static String getAppVersionName() {
        PackageManager packageManager = context.getPackageManager();
        String versionCode = "";

        try {
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException var4) {
            var4.printStackTrace();
        }

        return versionCode;
    }

    public static String getAppMetaData(String key) {
        if (TextUtils.isEmpty(key)) {
            return "";
        } else {
            String resultData = null;

            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

                return resultData;
            } catch (PackageManager.NameNotFoundException var4) {
                var4.printStackTrace();
                return "";
            }
        }
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;

        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException var3) {
            var3.printStackTrace(System.err);
        }

        if (info == null) {
            info = new PackageInfo();
        }

        return info;
    }

    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException var4) {
            var4.printStackTrace();
            return "";
        }
    }

    public static boolean isInstall(Context context, String packageNameString) {
        boolean flag = false;
        if (context != null && !TextUtils.isEmpty(packageNameString)) {
            PackageManager manager = context.getPackageManager();
            List<PackageInfo> allApps = manager.getInstalledPackages(0);

            for (int i = 0; i < allApps.size(); ++i) {
                String packageName = ((PackageInfo) allApps.get(i)).packageName;
                if (packageName.equals(packageNameString)) {
                    flag = true;
                }
            }

            return flag;
        } else {
            return flag;
        }
    }
}
