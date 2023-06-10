package com.moviehub.server.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//加密算法那边和这个类型转换的bug
@Service
public class DictLoader {
    private final ResourceLoader resourceLoader;

    @Autowired
    public DictLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public float[][] getMovieEmbeddingDict() throws IOException, CsvValidationException {
        Resource resource = resourceLoader.getResource("classpath:embeddingDict.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        csvReader.readNext();
        String[] line;
        List<float[]> data = new ArrayList<>();
        while ((line = csvReader.readNext()) != null) {
            float[] thisLine = new float[line.length - 2];
            for (int i = 2; i < line.length; i++){
                thisLine[i - 2] = Float.parseFloat(line[i]);
            }
            data.add(thisLine);
        }
        return data.toArray(new float[0][]);
    }

    public static void main(String[] args) {

    }
}

