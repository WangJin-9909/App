package com.example.app;

import android.app.Application;
import android.content.Context;

import com.example.app.utils.Constants;
import com.example.networklib.ServiceHelper;
import com.example.utils.AppInfoUtil;
import com.example.utils.AppLogger;

public class App extends Application {
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initTools();
        //初始化网络库
        ServiceHelper.initHost(Constants.HOST);
    }

    private void initTools() {
        //引入滴滴工具箱
        //List<AbstractKit> kits = new ArrayList<>();
        //kits.add(new MockKit());
        //DoraemonKit.install(this, kits, BuildConfig.DOKIT_PID);


        if (BuildConfig.DEBUG) {
            AppLogger.setIsLogEnable(true);
        }
        AppInfoUtil.setContext(getApplicationContext());
    }


    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
}
