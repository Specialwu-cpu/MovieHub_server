package com.moviehub.server.util;/*
@ClassNameResult
@AuthorDELL'
@Date2022/9/2923:57
@Version 1.0
*/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    public static Result success(){
        return new Result(0, "success", null);
    }

//    public static Result fail_1(){
//        return new Result(1, "网寄了", null);
//    }
//
//    public static Result fail_2(){
//        return new Result(2, "密码错了", null);
//    }

    public static Result ok() {
        Result r = new Result();
        r.setCode(0);
        r.setMessage("success");
        return r;
    }

    public static Result error(Integer code, String message, Map<String, Object> data){
        return new Result(code, message, data);
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
