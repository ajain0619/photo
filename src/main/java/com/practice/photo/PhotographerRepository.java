package com.practice.photo;

import com.practice.photo.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {

    List<Photographer> findByEventTypeContains(String event);
}
