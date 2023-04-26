package com.moviehub.server.controller;

import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserMovieController {
    @Resource
    private IMovieService iMovieService;


    @GetMapping(value = "/choice")
    public BaseResponse getMovieForVisitor(){
        return iMovieService.getMovieForVisitor();
    }
}
