package com.fuanpengcheng.it.pc.credit.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-03
 * @description:
 */
public class JsonUtils {
    private static Gson gson = new Gson();

    public static String toJsonString(Map<String, Object> map){
        return gson.toJson(map);
    }
}
