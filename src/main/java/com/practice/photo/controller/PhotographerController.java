package com.practice.photo.controller;


import com.practice.photo.PhotographerRepository;
import com.practice.photo.exception.PhotographerNotFoundException;
import com.practice.photo.model.Photographer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/photographers")
public class PhotographerController {

    @Autowired
    private PhotographerRepository repository;

    @GetMapping
    public List<Photographer> getAllPhotographers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Photographer getPhotographerById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PhotographerNotFoundException(id));
    }

    @GetMapping("/event/{eventType}")
    public List<Photographer> getPhotographersByEventType(@PathVariable String eventType) {
        return repository.findByEventTypeContains(eventType);
    }
}
