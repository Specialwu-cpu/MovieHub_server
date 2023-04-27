package com.moviehub.server.service;

import com.moviehub.server.util.BaseResponse;

/**
 * @Project ：server
 * @File ：ICastService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 18:08
 **/
public interface ICastService {
    BaseResponse findByCreditId(String creditId);
}
