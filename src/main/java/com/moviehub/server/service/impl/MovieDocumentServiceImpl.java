//package com.moviehub.server.service.impl;
//
//import com.moviehub.server.entity.Movie;
//import com.moviehub.server.entity.MovieDocument;
//import com.moviehub.server.repository.MovieDocumentRepository;
//import com.moviehub.server.repository.MovieRepository;
//import com.moviehub.server.service.IMovieDocumentService;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Project ：server
// * @File ：MovieDocumentServiceImpl.java
// * @IDE ：IntelliJ IDEA
// * @Author ：wsh
// * @Date ：2023/6/10 11:34
// **/
//@Service
//public class MovieDocumentServiceImpl implements IMovieDocumentService {
//
//    @Resource
//    private MovieRepository movieRepository;
//
//    @Resource
//    private MovieDocumentRepository movieDocumentRepository;
//
//    @Override
//    public void syncData() {
//        // 从数据库中获取需要同步的数据
//        List<Movie> movies = movieRepository.findAll();
//
//        List<MovieDocument> movieDocuments = new ArrayList<>();
//
//        // 将数据库中的数据转换为 MovieDocument 对象
//        for (Movie movie : movies) {
//            MovieDocument movieDocument = new MovieDocument();
//            movieDocument.setId(movie.getTmdbId());
//            movieDocument.setTitle(movie.getTitle());
//            // 其他字段...
//
//            movieDocuments.add(movieDocument);
//        }
//
//        // 将 MovieDocument 对象保存到 Elasticsearch 索引中
//        movieDocumentRepository.saveAll(movieDocuments);
//    }
//}
