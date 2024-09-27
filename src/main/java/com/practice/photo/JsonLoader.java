package com.practice.photo;


import com.practice.photo.model.Photographer;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonLoader {

    private final PhotographerRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonLoader(PhotographerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadJsonData() throws IOException {
        // Use ClassPathResource to load the JSON file from resources folder
        File file = new ClassPathResource("photographers.json").getFile();

        // Read and parse the JSON file
        List<Photographer> photographers = objectMapper.readValue(file, new TypeReference<List<Photographer>>() {});

        // Save all photographers to the database
        repository.saveAll(photographers);

        System.out.println("Photographer data loaded successfully.");
    }
}
