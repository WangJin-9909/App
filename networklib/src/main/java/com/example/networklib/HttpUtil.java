package com.example.networklib;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class HttpUtil {
    public static final String TAG = HttpUtil.class.getSimpleName();
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static String bodyToString(RequestBody body) {
        if (null == body) {
            return "";
        }
        try {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            //编码设为UTF-8
            Charset charset = UTF8;
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            //请求参数
            String requestStr = "";
            if (isPlaintext(buffer)) {
                requestStr = buffer.readString(charset);
            }
            return formatJson(requestStr);
        } catch (Exception e) {
            return "{\"err\": \"" + e.getMessage() + "\"}";
        }
    }

    public static String bodyToString(Response response) {
        String responseBodyString = "";
        try {
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            if (!bodyEncoded(response.headers())) {
                BufferedSource source = responseBody.source();
                // Buffer the entire body.
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                Charset charset = HttpUtil.UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(HttpUtil.UTF8);
                }

                if (HttpUtil.isPlaintext(buffer) && 0 != contentLength) {
                    responseBodyString = buffer.clone().readString(charset);
                }
            }
            responseBodyString = HttpUtil.formatJson(responseBodyString);
        } catch (Exception e) {
            e.printStackTrace();
            return responseBodyString;
        }
        return responseBodyString;
    }

    public static String formatJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return "";
        }
        String string;
        try {
            if (json.startsWith("{")) {
                string = new JSONObject(json).toString(2);
            } else if (json.startsWith("[")) {
                string = new JSONArray(json).toString(2);
            } else {
                string = json;
            }
        } catch (JSONException e) {
            string = json;
        } catch (OutOfMemoryError error) {
            string = "Output omitted because of Object size.";
        }

        return string;
    }

    public static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }

    public static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !"identity".equalsIgnoreCase(contentEncoding);
    }
}
