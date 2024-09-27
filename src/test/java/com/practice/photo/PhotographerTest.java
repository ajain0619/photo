package com.practice.photo;


import com.practice.photo.controller.PhotographerController;
import com.practice.photo.model.Photographer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotographerTest {

    @Mock
    private PhotographerRepository photographerRepository;

    @InjectMocks
    private PhotographerController controller;

    private List<Photographer> photographerList;

    @BeforeEach
    public void setUp() {

        Photographer photographer1 = new Photographer();
        photographer1.setId(1L);
        photographer1.setFirstName("Lorenzo");
        photographer1.setLastName("Fay");

        Photographer photographer2 = new Photographer();
        photographer2.setId(2L);
        photographer2.setFirstName("John");
        photographer2.setLastName("Doe");

        photographerList = Arrays.asList(photographer1, photographer2);
    }

    @Test
    public void shouldReturnAllPhotographers() {
        // When
        when(photographerRepository.findAll()).thenReturn(photographerList);

        // Then
        List<Photographer> result = controller.getAllPhotographers();

        assertEquals(2, result.size());
        assertEquals("Lorenzo", result.get(0).getFirstName());
        assertEquals("John", result.get(1).getFirstName());
    }
}
