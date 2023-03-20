//package com.moviehub.server.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.Parameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Project ：server
// * @File ：SwaggerConfig.java
// * @IDE ：IntelliJ IDEA
// * @Author ：wsh
// * @Date ：2023/3/20 13:14
// **/
//@Configuration
//@EnableSwagger2
//@EnableWebMvc
//public class SwaggerConfig {
//    @Value("${swagger.enable:false}")
//    private boolean enableSwagger;
//
//    @Bean
//    public Docket createRestApi() {
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        //设置所有接口的请求头
//        ticketPar.name("token").description("用户token信息")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                //header中的ticket参数非必填，传空也可以
//                .required(false).build();
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                //接口文档的相关信息
//                .apiInfo(apiInfo())
//                //配置是否启用swagger
//                .enable(enableSwagger)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.moviehub.server.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars);
//    }
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
