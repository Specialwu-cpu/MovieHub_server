package com.moviehub.server.controller;

import com.moviehub.server.service.ISingleMovieService;
import com.moviehub.server.util.BaseResponse;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/singleMovie")
@Tag(name = "SingleMovieController", description = "SingleMovieController")
@CrossOrigin(origins = "*")
public class SingleMovieController {
    @Resource
    private ISingleMovieService singleMovieService;

    @PostMapping("/rate")
    public BaseResponse rateSingleMovie(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        Long tmdb_id = ((Integer) map.get("tmdb_id")).longValue();
        float rate = ((Double) map.get("rate")).floatValue();
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn) {
            return singleMovieService.rateSingleMovie(email, tmdb_id, rate);
        }
        else {
            return BaseResponse.error("No Login!");
        }
    }

    @GetMapping("/searchMovie")
    public BaseResponse getSingleMovie(@RequestParam(value = "tmdb_id", defaultValue = "862") Long tmdb_id) {
        return singleMovieService.getSingleMovie(tmdb_id);
    }


    @GetMapping("/IJustBeUsedToTest")
    public BaseResponse forTest() throws CsvValidationException, IOException {
        return singleMovieService.rateSingleMovie("1923739674@qq.com", 137L, 3);
    }
}
