package com.example.networklib;

import com.example.utils.AppLogger;
import com.example.utils.CommonParam;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class LoggingInterceptor implements Interceptor {
    private String TAG = LoggingInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        log("--------------------------------------------------------------------Request--------------------------------------------------------------------");
        log("url : " + request.url());
        log("method : " + request.method());
        log("time :" + tookMs + "ms");

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        Headers requestHeaders = request.headers();
        for (int i = 0, count = requestHeaders.size(); i < count; i++) {
            String name = requestHeaders.name(i);
            if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                log(name + ": " + requestHeaders.value(i));
            }
        }
        if (hasRequestBody) {
            if (requestBody.contentType() != null) {
                log("Content-Type: " + requestBody.contentType());
            }
            if (requestBody.contentLength() != -1) {
                log("Content-Length: " + requestBody.contentLength());
            }
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            //编码设为UTF-8
            Charset charset = HttpUtil.UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(HttpUtil.UTF8);
            }
            if (HttpUtil.isPlaintext(buffer)) {
                //请求参数
                String str = buffer.readString(charset);
                log("body : " + str);
            }

        }


        log("--------------------------------------------------------------------Response--------------------------------------------------------------------");

        Headers headers = response.headers();
        for (
                int i = 0, count = headers.size();
                i < count; i++) {
            log(headers.name(i) + ": " + headers.value(i));
        }

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();


        if (HttpUtil.bodyEncoded(response.headers())) {
            log("--------------------------------------------------------------------End--------------------------------------------------------------------");
        } else {
            BufferedSource source = responseBody.source();
            // Buffer the entire body.
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset charset = HttpUtil.UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(HttpUtil.UTF8);
                } catch (UnsupportedCharsetException e) {
                    log("--------------------------------------------------------------------End--------------------------------------------------------------------");
                    return response;
                }
            }

            if (!HttpUtil.isPlaintext(buffer)) {
                log("--------------------------------------------------------------------End--------------------------------------------------------------------");
                return response;
            }

            if (contentLength != 0) {
                //获取Response的body的字符串 并打印
                log(buffer.clone().readString(charset));
            }
            log("--------------------------------------------------------------------End--------------------------------------------------------------------");
        }

        return response;
    }

    private void log(String log) {
        /*AppLogger.*/e(TAG, log);
    }

    public void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        // 长度小于等于限制直接打印
        if (length <= segmentSize) {
            AppLogger.e(tag, msg);
        } else {
            // 循环分段打印日志
            while (msg.length() > segmentSize) {
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                AppLogger.e(tag, logContent);
            }
            // 打印剩余日志
            AppLogger.e(tag, msg);
        }
    }
}
