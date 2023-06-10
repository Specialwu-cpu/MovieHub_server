package com.moviehub.server;

//import com.moviehub.server.service.IMovieDocumentService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class MovieHubServerApplication {

//    @Resource
//    private IMovieDocumentService iMovieDocumentService;

    public static void main(String[] args) {
        SpringApplication.run(MovieHubServerApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        // 在应用启动时执行一次数据同步操作
//        iMovieDocumentService.syncData();
//    }
}
