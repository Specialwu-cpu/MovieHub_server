package com.moviehub.server.util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/*
用于获得标准化的json格式的


@Example
{
    "msg": "成功",
    "code": 200,
    "data": {
        "mail_or_id": "20301138@bjtu.edu.cn",
        "user_name": "Zeason",
        "password": "u666666",
        "style_text": null,
        "graph": null
    }
}



 */

public class JsonCreater {
    public static HashMap<String, Object> getJson(Object data) throws JSONException {
        HashMap<String, Object> json = new HashMap<>();
        if (data == null) {

            json.put("code", ResponeCode.FAIL.value);
            json.put("msg", ResponeCode.FAIL.msg);
            json.put("data", data);
        }

        json.put("code", ResponeCode.SUCCESS.value);
        json.put("msg", ResponeCode.SUCCESS.msg);
        json.put("data", data);
        return json;
    }

    /**
     * 统一返回前台json格式
     * @param data
     * @return
     */
    public static HashMap<String, Object> getJson(Object data, String message) throws JSONException {
        HashMap<String, Object> json = new HashMap<>();
        json.put("code", ResponeCode.SUCCESS.value);
        json.put("msg", message);
        json.put("data", data);
        return json;
    }

    public static HashMap<String, Object> getJson(boolean success) throws JSONException {
        HashMap<String, Object> json = new HashMap<>();
        if (success) {
            json.put("code", ResponeCode.SUCCESS.value);
            json.put("msg", ResponeCode.SUCCESS.msg);
            json.put("data", null);
        }
        else {
            json.put("code", ResponeCode.FAIL.value);
            json.put("msg", ResponeCode.FAIL.msg);
            json.put("data", null);
        }
        return json;
    }
    /**
     * 统一返回前台json格式
     * @param data
     * @return
     */
    public static HashMap<String, Object> getJson(int code,Object data,String message) throws JSONException {
        HashMap<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("msg", message);
        json.put("data", data);
        return json;
    }

    /**
     * 处理返回的json
     * @param result 处理后的结构化数据
     * @param total
     * @return
     */
    public static HashMap<String, Object> getJsonForLog(Object result, int total) throws JSONException {
        HashMap<String, Object> json = new HashMap<>();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", total);
        json.put("data", result);
        return json;
    }

}
