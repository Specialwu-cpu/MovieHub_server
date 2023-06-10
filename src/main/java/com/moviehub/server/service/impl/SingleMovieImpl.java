package com.moviehub.server.service.impl;

import com.moviehub.server.entity.*;
import com.moviehub.server.repository.*;
import com.moviehub.server.service.ISingleMovieService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SingleMovieImpl implements ISingleMovieService {
    @Resource
    private MovieRepository movieRepository;

    @Resource
    private GenreRepository genreRepository;

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



    @Override
    public BaseResponse getSingleMovie(Long tmdb_id) {
        Movie thisMovie = movieRepository.findByTmdbId(tmdb_id);

        List<Genre> genres = genreRepository.findByTmdbId(tmdb_id);

        List<Countries> countries = countriesRepository.findByTmdbId(tmdb_id);

        List<Company> companies = companyRepository.findByTmdbId(tmdb_id);

        List<Keyword> keywords = keywordRepository.findByTmdbId(tmdb_id);

        List<Cast> casts = castRepository.findByTmdbId(tmdb_id);

        List<Crew> crews = crewRepository.findByTmdbId(tmdb_id);

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

        return BaseResponse.success(data);
    }
}
