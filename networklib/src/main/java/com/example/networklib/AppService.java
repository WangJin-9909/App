package com.example.networklib;

import com.example.app_module.bean.BaseResponse;
import com.example.app_module.bean.ListHaoKanBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface AppService {
    /**
     *获取短视频
     */
    @GET("getHaoKanVideo")
    Observable<BaseResponse<ListHaoKanBean>> getHaoKanVideo(@Query("page") int page, @Query("size") int size);



}
