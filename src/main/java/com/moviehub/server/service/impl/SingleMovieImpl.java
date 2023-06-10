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

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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





    @Override
    public BaseResponse getSingleMovie(Long tmdb_id) {
        Movie thisMovie = movieRepository.findByTmdbId(tmdb_id);

        List<Genre> genres = genreRepository.findByTmdbId(tmdb_id);

        List<Countries> countries = countriesRepository.findByTmdbId(tmdb_id);

        List<Company> companies = companyRepository.findByTmdbId(tmdb_id);

        List<Keyword> keywords = keywordRepository.findByTmdbId(tmdb_id);

        List<Cast> casts = castRepository.findByTmdbId(tmdb_id);

        List<Crew> crews = crewRepository.findByTmdbId(tmdb_id);



        HashMap<String, Object> data = new HashMap<>();

        data.put("Movie", thisMovie);
        data.put("genres", genres);
        data.put("coutries", countries);
        data.put("companies", companies);
        data.put("keywords", keywords);
        data.put("casts", casts);
        data.put("crews", crews);

        return BaseResponse.success(data);



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
        UserFeature thisUsersFeature = userFeatureRepository.findUserFeatureByMailOrId(email);
        ValueOperations<String, Object> setUserFeature = redisTemplate.opsForValue();
        setUserFeature.set("UserFeatureOf" + email, thisUsersFeature, 30, TimeUnit.MINUTES);
        return BaseResponse.success("Rating successfully!");

    }
}
