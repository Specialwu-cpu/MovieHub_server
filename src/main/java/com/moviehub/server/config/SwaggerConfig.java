package com.moviehub.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) // 1.SWAGGER_2
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moviehub.server.controller")) // 2.设置扫描路径
                .build();
    }

}
//
//    /**
//     * api文档的详细信息函数
//     * @author wsh
//     * @return springfox.documentation.service.ApiInfo
//     * @date 2023/3/20 13:14
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Movie Hub API")
//                .description("Movie Hub API文档")
//                .version("1.0.0")
//                .contact(new Contact("wsh", "shuhaiwork996@gamil.com", "1307053712@qq.com"))
//                .build();
//    }
//}
