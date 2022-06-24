package com.example.app;

import android.app.Application;
import android.content.Context;

import com.example.app.utils.Constants;
import com.example.networklib.ServiceHelper;
import com.example.utils.AppInfoUtil;
import com.example.utils.AppLogger;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class App extends Application {
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        handleSSLHandshake();
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

    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }


}
