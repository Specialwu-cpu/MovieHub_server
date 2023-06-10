//package com.moviehub.server.entity;
//
///**
// * @Project ：server
// * @File ：MovieDocument.java
// * @IDE ：IntelliJ IDEA
// * @Author ：wsh ruan
// * @Date ：2023/6/10 10:39
// **/
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.CompletionField;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//@Document(indexName = "movies")
//public class MovieDocument {
//    @Id
//    private Long id;
//
//    @CompletionField(analyzer = "standard", searchAnalyzer = "standard")
//    private String title;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    // 其他字段...
//
//    // Getter和Setter方法...
//}
//
