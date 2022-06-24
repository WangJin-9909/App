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
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST
    Observable<BaseResponse<JsonObject>> executePostForm(@Url String url, @Body Object param);

    /**
     *
     * @param url
     * @param map  HashMap即可
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @POST
    Observable<JsonObject> executePostJson(@Url String url, @Body Object map);

    @POST
    Observable<BaseResponse<JsonObject>> executePost(@Url String var1, @Body Object var2, @Header("Content-Type") String var3);

    @Headers({"Content-Type: application/json"})
    @POST
    Observable<JsonObject> executePostNew(@Url String url, @Body Object map, @HeaderMap Map<String, String> headMap);

}
