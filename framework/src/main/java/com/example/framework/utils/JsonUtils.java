package com.example.framework.utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static Gson gson = new Gson();

    public static <T> T parseJson(String response, Class<T> clazz) {
        try {
            return gson.fromJson(response, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
