package com.example.networklib;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface AppService {
    //wanandroid注册
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse> signup(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("repassword") String repassword);


}
