package com.moviehub.server.service.impl;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.entity.Rating;
import com.moviehub.server.entity.UserFeature;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.repository.RatingRepository;
import com.moviehub.server.repository.UserFeatureRepository;
import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import com.moviehub.server.util.DictLoader;
import com.moviehub.server.util.MathR;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.*;
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

    private final ResourceLoader resourceLoader;
    private OrtSession session;

    private OrtEnvironment environment = OrtEnvironment.getEnvironment();


    @Resource
    private DictLoader dictLoader;



    @Resource
    private MovieRepository movieRepository;

    @Resource
    private UserFeatureRepository userFeatureRepository;

    @Resource
    private RatingRepository ratingRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public MovieServiceImpl(ResourceLoader resourceLoader) throws OrtException {
        this.resourceLoader = resourceLoader;


        String modelPath = "C:\\Users\\19237\\Desktop\\MovieHub_sever\\src\\main\\resources\\userTower.onnx";


        OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
        session = environment.createSession(modelPath, sessionOptions);
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    //还得想想怎么弄分页瀑布
    @Override
    public BaseResponse getMovieForYou(int page, String email) throws CsvValidationException, IOException, OrtException {
        final String key = "UserFeatureOf" + email;
        ValueOperations<String, Object> this_gays_email_to_his_feature = redisTemplate.opsForValue();
        UserFeature userFeature = (UserFeature) this_gays_email_to_his_feature.get(key);//这边可能考虑用leveldb
        if (userFeature == null){
            //这边是如果用的redis，缓存里没有，就去mysql找然后存入缓存，记得改拦截器
            userFeature = userFeatureRepository.findUserFeatureByMailOrId(email);
            this_gays_email_to_his_feature.set(key, userFeature, 30, TimeUnit.MINUTES);
        }
        float[][] movieEmbeddingDict = dictLoader.getMovieEmbeddingDict();
        Long[] tmdbIdList = dictLoader.getTmdbId();
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


        //转为2d
        float[][] finalInput = new float[1][];
        finalInput[0] = userinput;

        //转为tensor 妈的java真几把麻烦
        OnnxTensor input = OnnxTensor.createTensor(environment, finalInput);
        OrtSession.Result result = session.run(Collections.singletonMap("input.1", input));

        //转为java的格式
        float[][] output = (float[][]) result.get(0).getValue();
        float[] realOut = output[0];
        HashMap<Long, Float> tmdbAndScore = new HashMap<>();
        for (int i = 0; i < movieEmbeddingDict.length; i++) {
            tmdbAndScore.put(tmdbIdList[i], MathR.dot(realOut, movieEmbeddingDict[i]));
        }

        List<Map.Entry<Long, Float>> list = new ArrayList<>(tmdbAndScore.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));



        if (page == 0) {

            List<Movie> popularMovie = movieRepository.findMovieMostPopular();
            List<Movie> earnMovie = movieRepository.findMovieMostEarn();
            List<Movie> todayMovie = movieRepository.findTodayMovie();

            List<Long> tmdbSelected = new ArrayList<>();
            for (int i = 0; i < 20; i++){
                tmdbSelected.add(list.get(i).getKey());
            }
            List<Movie> recommend = movieRepository.findAllByTmdbIds(tmdbSelected);
            HashMap<String, List<Movie>> data = new HashMap<>();
            data.put("Popular", popularMovie);
            data.put("HighestBox", earnMovie);
            data.put("Today", todayMovie);
            data.put("Recommend", recommend);
            return BaseResponse.success(data);
        }
        else {
            List<Long> tmdbSelected = new ArrayList<>();
            for (int i = page * 20; i < page * 20 + 20; i++){
                tmdbSelected.add(list.get(i).getKey());
            }
            List<Movie> recommend = movieRepository.findAllByTmdbIds(tmdbSelected);
            HashMap<String, List<Movie>> data = new HashMap<>();
            data.put("Recommend", recommend);
            return BaseResponse.success(data);
        }
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

}
