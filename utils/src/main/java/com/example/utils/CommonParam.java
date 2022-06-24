package com.example.utils;

import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;

public class CommonParam {
    private volatile static CommonParam instance;

    private static HashMap<String, String> parameters = null;

    private CommonParam() {
        String app_version = AppInfoUtil.getAppVersionName();
        String market = AppInfoUtil.getAppMetaData("CHANNEL_NAME");
        parameters = new HashMap<>();
        parameters.put("app_platform", "Android");
        parameters.put("app_version", TextUtils.isEmpty(app_version) ? "" : app_version);
        parameters.put("market", TextUtils.isEmpty(market) ? "" : market);
        parameters.put("app_devicetype", TextUtils.isEmpty(Build.MODEL) ? "" : Build.MODEL);
        parameters.put("app_system", TextUtils.isEmpty(Build.VERSION.RELEASE) ? "" : Build.VERSION.RELEASE);

    }

    public static CommonParam getInstance() {
        if (instance == null) {
            synchronized (CommonParam.class) {
                if (instance == null) {
                    instance = new CommonParam();
                }
            }
        }
        return instance;
    }

    public HashMap getMap() {

        return parameters;
    }
}
