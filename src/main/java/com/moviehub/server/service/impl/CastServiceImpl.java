package com.moviehub.server.service.impl;

import com.moviehub.server.dto.CastDetailDTO;
import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Credit;
import com.moviehub.server.entity.Crew;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.repository.CastRepository;
import com.moviehub.server.repository.CreditRepository;
import com.moviehub.server.repository.CrewRepository;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.service.ICastService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private CrewRepository crewRepository;

    @Resource
    private CreditRepository creditRepository;

    @Override
    public BaseResponse findByCreditId(String creditId) {
        Cast cast = castRepository.findByCreditId(creditId);
        if (cast == null) {
            return BaseResponse.error("Cast not found");
        }
//        Credit credit = creditRepository.findByCreditId(creditId);
        List<Movie> moviesByCast = movieRepository.findMoviesByCast(cast.getTmdbId());
        Crew crew = crewRepository.findByCreditId(creditId);
        List<Movie> moviesByCrew = movieRepository.findMoviesByCrew(crew.getTmdbId());
        // 将演员Cast的个人信息和所参演的电影信息封装到一个对象中
        CastDetailDTO castDetailDTO = new CastDetailDTO(cast, moviesByCast, crew, moviesByCrew);
        return BaseResponse.success(castDetailDTO);
//        return BaseResponse.success(credit);
    }
}
