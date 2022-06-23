package com.example.app;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTools();

    }

    private void initTools() {
        //引入滴滴工具箱
        //List<AbstractKit> kits = new ArrayList<>();
        //kits.add(new MockKit());
        //DoraemonKit.install(this, kits, BuildConfig.DOKIT_PID);

    }
}
