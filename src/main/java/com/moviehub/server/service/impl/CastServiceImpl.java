package com.moviehub.server.service.impl;

import com.moviehub.server.dto.CastDetailDTO;
import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.repository.CastRepository;
import com.moviehub.server.service.ICastService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Project ：server
 * @File ：CastServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 18:10
 **/
@Service
public class CastServiceImpl implements ICastService {

    @Resource
    private CastRepository castRepository;

    @Override
    public BaseResponse findByCreditId(String creditId) {
        Cast cast = castRepository.findByCreditId(creditId);
        if (cast == null) {
            return BaseResponse.error("Cast not found");
        }
        List<Movie> moviesByCast = castRepository.findMoviesByTmdbId(cast.getTmdbId());

        // 将演员Cast的个人信息和所参演的电影信息封装到一个对象中
        CastDetailDTO castDetailDTO = new CastDetailDTO(cast, moviesByCast);
        return BaseResponse.success(castDetailDTO);
    }
}
