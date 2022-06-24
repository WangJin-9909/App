package com.example.networklib;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.BuildConfig;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServiceHelper {
    private static ServiceHelper instance = null;
    private static AppService appService;
    public static String HOST;

    public static void initHost(String HOST) {
        ServiceHelper.HOST = HOST;
    }

    public static AppService getNetworkServer() {
        if (appService == null) {
            appService = ServiceHelper.getInstance().getAppService();
        }
        return appService;
    }

    public static ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
        }
        return instance;
    }

    public static <U> Observable.Transformer<U, U> getDefaultScheduler() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public AppService getAppService() {
        OkHttpClient client = getOkHttpClient(true);
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(AppService.class);
    }

    private OkHttpClient getOkHttpClient(boolean isEncryption) {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        //自定义长连接保持时间
        ConnectionPool pool = new ConnectionPool(5, 20, TimeUnit.SECONDS);
        builder.connectionPool(pool);

        //if (BuildConfig.DEBUG) {
            LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
            builder.addInterceptor(loggingInterceptor);
        //}
        return builder.build();
    }
}
