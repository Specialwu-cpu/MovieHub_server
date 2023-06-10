package com.moviehub.server.service.impl;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import com.moviehub.server.entity.Movie;
//import com.moviehub.server.entity.MovieDocument;
//import com.moviehub.server.repository.MovieDocumentRepository;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import com.moviehub.server.util.DictLoader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Project ：MovieHub-server
 * @File ：MovieServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/25 11:34
 **/
@Service
public class MovieServiceImpl implements IMovieService {
    @Resource
    private DictLoader dictLoader;
    OrtEnvironment environment = OrtEnvironment.getEnvironment();
    OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
//    OrtSession session = environment.createSession("../util/userTower.onnx");

    @Resource
    private MovieRepository movieRepository;

//    @Resource
//    private MovieDocumentRepository movieDocumentRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public MovieServiceImpl() throws OrtException {
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public BaseResponse getMovieForYou(int page, String email) throws CsvValidationException, IOException {
        final String key = "gay_feature:" + email;
        ValueOperations<String, Object> this_gays_email_to_his_feature = redisTemplate.opsForValue();
        if (page == 0) {
            List<Movie> popularMovie = movieRepository.findMovieMostPopular();
            List<Movie> earnMovie = movieRepository.findMovieMostEarn();
            List<Movie> todayMovie = movieRepository.findTodayMovie();

            System.out.println(dictLoader.getMovieEmbeddingDict().length);
        }
        return BaseResponse.success();
    }
    @Override
    public BaseResponse getMovieForVisitor(int page) {
        final String key = "movie:choice_guest_start" + page;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        if (page == 0){
            HashMap<String, List<Movie>> data = (HashMap<String, List<Movie>>) valueOperations.get(key);

            if (data == null){
                List<Movie> popularMovie = movieRepository.findMovieMostPopular();
                List<Movie> earnMovie = movieRepository.findMovieMostEarn();
                List<Movie> todayMovie = movieRepository.findTodayMovie();
                List<Movie> recommendMovie = movieRepository.findForRecommendGuest(20, 20 * page);

                data = new HashMap<>();
                data.put("Popular", popularMovie);
                data.put("HighestBox", earnMovie);
                data.put("Today", todayMovie);
                data.put("Recommend", recommendMovie);
                valueOperations.set(key, data, 1, TimeUnit.HOURS);
            }
            //都给客户端
            return BaseResponse.success(data);
        }
        else {
            List<Movie> data = (List<Movie>) valueOperations.get(key);
            if (data == null){
                List<Movie> newData = movieRepository.findForRecommendGuest(20, 20 * page);
                valueOperations.set(key, newData, 60 - page, TimeUnit.MINUTES);

                return BaseResponse.success(newData);
            }
            return BaseResponse.success(data);
        }

    }

    // todo 可加redis，还未加
    @Override
    public BaseResponse searchMoviews(String query) {
        List<Movie> moviesByTitle = movieRepository.findByTitleContainingIgnoreCase(query);
        List<Movie> moviesByKeyword = movieRepository.findByKeywordsContainingIgnoreCase(query);
        List<Movie> moviesByCrew = movieRepository.findByCrewContainingIgnoreCase(query);
        List<Movie> moviesByCast = movieRepository.findByCastContainingIgnoreCase(query);

        // 合并搜索结果
        List<Movie> mergedMovies = new ArrayList<>();
        mergedMovies.addAll(moviesByTitle);
        mergedMovies.addAll(moviesByKeyword);
        mergedMovies.addAll(moviesByCrew);
        mergedMovies.addAll(moviesByCast);

        // 去重，根据电影的唯一标识（如ID）进行去重
        Set<Long> uniqueMovieIds = new HashSet<>();
        List<Movie> deduplicatedMovies = new ArrayList<>();
        for (Movie movie : mergedMovies) {
            if (uniqueMovieIds.add(movie.getTmdbId())) {
                deduplicatedMovies.add(movie);
            }
        }

        return BaseResponse.success(deduplicatedMovies);

    }

    @Override
    public BaseResponse addMovie(boolean adult, String homepage, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, String status, String tagline, String title, Long revenue, Long budget, LocalDate releaseDate, Integer runtime, Float voteAverage, Integer voteCount) {
        List<Long> existingTmdbIds = movieRepository.findAllTmdbIds();
        Long newTmdbId;
        Random random = new Random();
        do {
            newTmdbId = (long) (random.nextInt(Integer.MAX_VALUE) + 1);
        } while (existingTmdbIds.contains(newTmdbId));
        Movie movie = new Movie();
        movie.setTmdbId(newTmdbId);
        movie.setAdult(adult);
        movie.setHomepage(homepage);
        movie.setOriginalLanguage(originalLanguage);
        movie.setOriginalTitle(originalTitle);
        movie.setOverview(overview);
        movie.setPopularity(popularity);
        movie.setPosterPath(posterPath);
        movie.setStatus(status);
        movie.setTagline(tagline);
        movie.setTitle(title);
        movie.setRevenue(revenue);
        movie.setBudget(budget);
        movie.setReleaseDate(releaseDate);
        movie.setRuntime(runtime);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);
        movieRepository.save(movie);
        return BaseResponse.success(newTmdbId);
    }

    @Override
    public BaseResponse updateMovie(Long tmdbId, boolean adult, String homepage, String originalLanguage, String originalTitle, String overview, double popularity, String posterPath, String status, String tagline, String title, long revenue, long budget, LocalDate releaseDate, int runtime, float voteAverage, int voteCount) {
        Movie movie = new Movie();
        movie.setTmdbId(tmdbId);
        movie.setAdult(adult);
        movie.setHomepage(homepage);
        movie.setOriginalLanguage(originalLanguage);
        movie.setOriginalTitle(originalTitle);
        movie.setOverview(overview);
        movie.setPopularity(popularity);
        movie.setPosterPath(posterPath);
        movie.setStatus(status);
        movie.setTagline(tagline);
        movie.setTitle(title);
        movie.setRevenue(revenue);
        movie.setBudget(budget);
        movie.setReleaseDate(releaseDate);
        movie.setRuntime(runtime);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);
        movieRepository.save(movie);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse deleteMovieByTmdbId(Long tmdbId) {
        movieRepository.deleteById(String.valueOf(tmdbId));
        return BaseResponse.success();
    }

//    @Override
//    public BaseResponse searchMovieSuggest(String query) {
//        // 自动补全查询
//        List<MovieDocument> suggestions = movieDocumentRepository.findByTitleSuggest(query);
//
//        // 其他业务逻辑
//
//        return BaseResponse.success(suggestions);
//    }


}
