package com.moviehub.server.service.impl;

import com.moviehub.server.entity.*;
import com.moviehub.server.repository.*;
import com.moviehub.server.service.ISingleMovieService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.*;

@Service
public class SingleMovieImpl implements ISingleMovieService {
    @Resource
    private MovieRepository movieRepository;

    @Resource
    private GenreRepository genreRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private CountriesRepository countriesRepository;

    @Resource
    private CompanyRepository companyRepository;

    @Resource
    private KeywordRepository keywordRepository;

    @Resource
    private CrewRepository crewRepository;

    @Resource
    private CastRepository castRepository;

    @Resource
    private CreditRepository creditRepository;

    @Resource
    private RatingRepository ratingRepository;

    @Resource
    private UserFeatureRepository userFeatureRepository;

    @Resource
    private CommentRepository commentRepository;





    @Override
    public BaseResponse getSingleMovie(Long tmdb_id) {
        Movie thisMovie = movieRepository.findByTmdbId(tmdb_id);

        List<Genre> genres = genreRepository.findByTmdbId(tmdb_id);

        List<Countries> countries = countriesRepository.findByTmdbId(tmdb_id);

        List<Company> companies = companyRepository.findByTmdbId(tmdb_id);

        List<Keyword> keywords = keywordRepository.findByTmdbId(tmdb_id);

        List<Cast> casts = castRepository.findByTmdbId(tmdb_id);

        List<Crew> crews = crewRepository.findByTmdbId(tmdb_id);

        List<Comment> comments = commentRepository.findByTmdbId(tmdb_id);

        Set<Movie> uniqueMovies = new HashSet<>();

        for (Keyword keyword : keywords) {
            Integer keywordId = keyword.getKeywordID();
            List<Movie> movies = movieRepository.findMoviesByKeywordId(keywordId);
            uniqueMovies.addAll(movies);
        }

        List<Movie> combinedMovies = new ArrayList<>(uniqueMovies);

//        List<Movie> otherMoviesByGenres = movieRepository.findMoviesByGenre();


        HashMap<String, Object> data = new HashMap<>();

        data.put("Movie", thisMovie);
        data.put("genres", genres);
        data.put("coutries", countries);
        data.put("companies", companies);
        data.put("keywords", keywords);
        data.put("casts", casts);
        data.put("crews", crews);
        data.put("otherMovies", combinedMovies);
        data.put("comment", comments);

        return BaseResponse.success(data);



    }

    @Transactional
    @Override
    public BaseResponse commentSingleMovie(String email, Long tmdb_id, String comment) {

        commentRepository.insertNewComment(email, tmdb_id, comment, new Timestamp(System.currentTimeMillis()));
        return BaseResponse.success();
    }

    @Transactional
    @Override
    public BaseResponse rateSingleMovie(String email, Long tmdb_id, float rate) {
        Rating rating = new Rating();
        rating.setIdOrMail(email);
        rating.setTmdbId(tmdb_id);
        rating.setRating(rate);
        ratingRepository.save(rating);
        List<Genre> thisMovieGenres = genreRepository.findByTmdbId(tmdb_id);
        for (int i = 0; i < thisMovieGenres.size(); i++) {
            int genreId = thisMovieGenres.get(i).getId();
            userFeatureRepository.updateGenreN(genreId, email);
        }


        //用户特征存入缓存，更新推荐用的东西
        UserFeature userFeature = userFeatureRepository.findUserFeatureByMailOrId(email);
        ValueOperations<String, Object> setUserFeature = redisTemplate.opsForValue();
        float[] userinput = new float[24];
        userinput[0] = userFeature.getRatingMean();
        userinput[1] = userFeature.getRatingCount().floatValue();
        userinput[2] = userFeature.getTimestampMax().floatValue();
        userinput[3] = userFeature.getRuntimeMean();
        userinput[4] = userFeature.getGenre18().floatValue();
        userinput[5] = userFeature.getGenre80().floatValue();
        userinput[6] = userFeature.getGenre35().floatValue();
        userinput[7] = userFeature.getGenre28().floatValue();
        userinput[8] = userFeature.getGenre53().floatValue();
        userinput[9] = userFeature.getGenre12().floatValue();
        userinput[10] = userFeature.getGenre878().floatValue();
        userinput[11] = userFeature.getGenre16().floatValue();
        userinput[12] = userFeature.getGenre10751().floatValue();
        userinput[13] = userFeature.getGenre10749().floatValue();
        userinput[14] = userFeature.getGenre9648().floatValue();
        userinput[15] = userFeature.getGenre10402().floatValue();
        userinput[16] = userFeature.getGenre27().floatValue();
        userinput[17] = userFeature.getGenre14().floatValue();
        userinput[18] = userFeature.getGenre99().floatValue();
        userinput[19] = userFeature.getGenre10752().floatValue();
        userinput[20] = userFeature.getGenre37().floatValue();
        userinput[21] = userFeature.getGenre36().floatValue();
        userinput[22] = userFeature.getGenre10769().floatValue();
        userinput[23] = userFeature.getGenre10770().floatValue();
        setUserFeature.set("UserFeatureOf" + email, userinput, 30, TimeUnit.MINUTES);
        return BaseResponse.success("Rating successfully!");

    }
}
