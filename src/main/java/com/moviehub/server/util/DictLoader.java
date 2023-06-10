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


    public Long[] getTmdbId() throws CsvValidationException, IOException {
        Resource resource = resourceLoader.getResource("classpath:embeddingDict.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        csvReader.readNext();
        String[] line;
        Long[] tmdbIdList = new Long[45100];
        int nn = 0;
        while ((line = csvReader.readNext()) != null) {
            tmdbIdList[nn] = Long.parseLong(line[2]);
            nn ++;
        }
        return tmdbIdList;
    }
    public float[][] getMovieEmbeddingDict() throws IOException, CsvValidationException {
        Resource resource = resourceLoader.getResource("classpath:embeddingDict.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        csvReader.readNext();
        String[] line;
        List<float[]> data = new ArrayList<>();
        while ((line = csvReader.readNext()) != null) {
            float[] thisLine = new float[line.length - 3];
            for (int i = 3; i < line.length; i++){
                thisLine[i - 3] = Float.parseFloat(line[i]);
            }
            data.add(thisLine);
        }
        return data.toArray(new float[0][]);
    }

    public static void main(String[] args) {

    }
}

