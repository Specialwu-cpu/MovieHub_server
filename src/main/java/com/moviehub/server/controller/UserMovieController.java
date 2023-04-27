package com.moviehub.server.controller;

import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "UserMovieController", description = "UserMovieController")
public class UserMovieController {
    @Resource
    private IMovieService iMovieService;



    @Operation(summary = "movie page", description = "get main page(just like 'choice' in Tencent Video), You can choose" +
            "the page number.If page number is '0', and it will return the main page(consist of popularity, revenue, today's movie list)" +
            " I told Li Ziyang at that night in 704. " +
            "Or it will return the recommendation for guest",
            parameters = {@Parameter(name = "page", description = "the page you need")})
    @ApiResponse(responseCode = "200", description = "success!")
    @GetMapping(value = "/choice")
    public BaseResponse getMovieForVisitor(@RequestParam(value = "page", defaultValue = "0") int page){
        return iMovieService.getMovieForVisitor(page);
    }
}
