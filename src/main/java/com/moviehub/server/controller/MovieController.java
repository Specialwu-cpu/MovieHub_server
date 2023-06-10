package com.moviehub.server.controller;

/**
 * @Project ：MovieHub-server
 * @File ：MovieController.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/25 11:29
 **/
import com.moviehub.server.entity.Movie;
import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Tag(name = "MovieController", description = "MovieController")
@CrossOrigin(origins = "*")
public class MovieController {

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

    @Operation(summary = "movie page", description = "get main page(just like 'choice' in Tencent Video), You can choose" +
            "the page number.If page number is '0', and it will return the main page(consist of popularity, revenue, today's movie list)" +
            " I told Li Ziyang at that night in 704. " +
            "Or it will return the recommendation for guest",
            parameters = {@Parameter(name = "page", description = "the page you need")})
    @ApiResponse(responseCode = "200", description = "success!")
    @GetMapping(value = "/choice")
    public BaseResponse getMovieForVisitor(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") int page){
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return BaseResponse.error("nimasile");
        }
        else {
            System.out.println("我喜欢我");
            return iMovieService.getMovieForVisitor(page);
        }
    }

    @GetMapping("/search")
    public BaseResponse searchMovies(@RequestParam("query") String query) {
        return iMovieService.searchMoviews(query);
    }

//    @GetMapping("/searchSuggest")
//    public BaseResponse searchSuggestMovies(@RequestParam("query") String query) {
//        return iMovieService.searchMovieSuggest(query);
//    }

    @GetMapping("/IJustBeUsedToTest")
    public BaseResponse forTest() throws CsvValidationException, IOException {
        return iMovieService.getMovieForYou(0, "20301138@bjtu.edu.cn");
    }

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
    @PostMapping("/addmovies")
    public BaseResponse addMovie(
            HttpServletRequest request,
            @RequestBody Map<String, String> map) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return BaseResponse.error("nimasile");
        }
        else {
            System.out.println("我喜欢我");
            return iMovieService.addMovie(
                    Boolean.parseBoolean(map.get("adult")),
                    map.get("homepage"),
                    map.get("original_language"),
                    map.get("original_title"),
                    map.get("overview"),
                    Double.parseDouble(map.get("popularity")),
                    map.get("poster_path"),
                    map.get("status"),
                    map.get("tagline"),
                    map.get("title"),
                    Long.parseLong(map.get("revenue")),
                    Long.parseLong(map.get("budget")),
                    LocalDate.parse(map.get("release_date")),
                    Integer.parseInt(map.get("runtime")),
                    Float.parseFloat(map.get("vote_average")),
                    Integer.parseInt(map.get("vote_count")));
        }
//        return iMovieService.addMovie(
//                Boolean.parseBoolean(map.get("adult")),
//                map.get("homepage"),
//                map.get("original_language"),
//                map.get("original_title"),
//                map.get("overview"),
//                Double.parseDouble(map.get("popularity")),
//                map.get("poster_path"),
//                map.get("status"),
//                map.get("tagline"),
//                map.get("title"),
//                Long.parseLong(map.get("revenue")),
//                Long.parseLong(map.get("budget")),
//                LocalDate.parse(map.get("release_date")),
//                Integer.parseInt(map.get("runtime")),
//                Float.parseFloat(map.get("vote_average")),
//                Integer.parseInt(map.get("vote_count")));
//        return null;
    }


    @PutMapping("/{tmdb_id}/update")
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
            HttpServletRequest request,
            @Parameter(description = "The ID of the movie to update", required = true)
            @PathVariable("tmdb_id") Long tmdbId,
            @RequestBody Map<String, String> map) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iMovieService.updateMovie(tmdbId,
                    Boolean.parseBoolean(map.get("adult")),
                    map.get("homepage"),
                    map.get("original_language"),
                    map.get("original_title"),
                    map.get("overview"),
                    Double.parseDouble(map.get("popularity")),
                    map.get("poster_path"),
                    map.get("status"),
                    map.get("tagline"),
                    map.get("title"),
                    Long.parseLong(map.get("revenue")),
                    Long.parseLong(map.get("budget")),
                    LocalDate.parse(map.get("release_date")),
                    Integer.parseInt(map.get("runtime")),
                    Float.parseFloat(map.get("vote_average")),
                    Integer.parseInt(map.get("vote_count")));
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
//        return null;
    }

    //这个该删。首先没用，其次会影响其他的
//    @Operation(summary = "Get a movie by tmdb_id")
//    @GetMapping("/{tmdb_id}")
//    public ResponseEntity<Movie> getMovieByTmdbId(
//            @Parameter(in = ParameterIn.PATH, description = "The tmdb_id of the movie", required = true)
//            @PathVariable("tmdb_id") Long tmdbId) {
//        return null;
//        return iMovieService.getMovieByTmdbId(tmdbId);
//    }

    @Operation(summary = "Delete a movie by tmdb_id")
    @DeleteMapping("/{tmdb_id}/delete")
    public BaseResponse deleteMovieByTmdbId(
            HttpServletRequest request,
            @Parameter(in = ParameterIn.PATH, description = "The tmdb_id of the movie to delete", required = true)
            @PathVariable("tmdb_id") Long tmdbId) {
//        return null;
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iMovieService.deleteMovieByTmdbId(tmdbId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }

}
