package com.moviehub.server.controller;

import com.moviehub.server.entity.Cast;
import com.moviehub.server.repository.CastRepository;
import com.moviehub.server.service.ICastService;
import com.moviehub.server.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project ：server
 * @File ：CastController.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 18:12
 **/
@RestController
@RequestMapping("/credits")
@Tag(name = "CastController", description = "CastController")
@CrossOrigin(origins = "*")
public class CastController {

    @Resource
    private CastRepository castRepository;

    @Resource
    private ICastService iCastService;

    @GetMapping("/casts/{tmdbId}")
    public BaseResponse<List<Cast>> getCastsByTmdbId(@PathVariable Long tmdbId) {
        List<Cast> castsByMovies = castRepository.findByMovieTmdbId(tmdbId);
        return BaseResponse.success(castsByMovies);
    }

    @Operation(summary = "Get cast information by credit ID")
    @GetMapping("/{creditId}")
    public BaseResponse getCastDetail(@Parameter(description = "Credit ID") @PathVariable String creditId) {
        return iCastService.findByCreditId(creditId);
    }
}

