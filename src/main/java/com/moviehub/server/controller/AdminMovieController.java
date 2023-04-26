package com.moviehub.server.controller;

/**
 * @Project ：MovieHub-server
 * @File ：MovieController.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 11:29
 **/
import com.moviehub.server.entity.Movie;
import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Tag(name = "MovieController", description = "MovieController")
public class AdminMovieController {

    @Resource
    private IMovieService iMovieService;

    @GetMapping("/")
    @Operation(summary = "Get all movies",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all movies successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)))
            })
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = iMovieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Add a movie",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Add movie successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid movie",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)))
            })
    public BaseResponse addMovie(
            @Parameter(description = "The movie to add", required = true)
            @RequestBody Map<String, String> map) {
        return null;
//        return iMovieService.addMovie(map);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie",
            parameters = @Parameter(name = "id", description = "The ID of the movie to update", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update movie successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid movie",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Movie not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class)))
            })
    public BaseResponse updateMovie(
            @Parameter(description = "The ID of the movie to update", required = true)
            @PathVariable("id") Long id,
            @RequestBody Map<String, String> map) {
        return null;
//        return iMovieService.updateMovie(id, map);
    }

    @Operation(summary = "Get a movie by tmdb_id")
    @GetMapping("/{tmdb_id}")
    public ResponseEntity<Movie> getMovieByTmdbId(
            @Parameter(in = ParameterIn.PATH, description = "The tmdb_id of the movie", required = true)
            @PathVariable("tmdb_id") Long tmdbId) {
        return null;
//        return iMovieService.getMovieByTmdbId(tmdbId);
    }

    @Operation(summary = "Delete a movie by tmdb_id")
    @DeleteMapping("/{tmdb_id}")
    public ResponseEntity<HttpStatus> deleteMovieByTmdbId(
            @Parameter(in = ParameterIn.PATH, description = "The tmdb_id of the movie to delete", required = true)
            @PathVariable("tmdb_id") Long tmdbId) {
        return null;
//        return iMovieService.deleteMovieByTmdbId(tmdbId);
    }

}