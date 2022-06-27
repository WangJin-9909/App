package com.example.networklib;


import com.example.networklib.bean.VersionBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;


public interface AppService {



    @GET("get_version")
    Observable<BaseResponse<VersionBean>> getVersion();

}
