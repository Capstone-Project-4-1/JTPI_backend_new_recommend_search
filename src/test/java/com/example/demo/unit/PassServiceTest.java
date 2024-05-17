package com.example.demo.unit;
import com.example.demo.DTO.PassSearchResultDTO;
import com.example.demo.DTO.SlideShowPassDTO;
import com.example.demo.Repository.PassRepository;
import com.example.demo.SearchParameters;
import com.example.demo.Service.PassService;
import com.example.demo.entity.PassInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PassServiceTest {

    @Mock
    private PassRepository passRepository;

    @InjectMocks
    private PassService passService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFetchSlideShowNewPasses() {
        List<PassInformation> slideShowNewPasses = Collections.emptyList();
        when(passRepository.findSlideShowNewPasses()).thenReturn(slideShowNewPasses);

        List<SlideShowPassDTO> result = passService.fetchSlideShowNewPasses();

        assertEquals(0, result.size());
    }

    @Test
    void testFetchSlideShowRecommendedPasses() {
        List<PassInformation> slideShowRecommendedPasses = Collections.emptyList();
        when(passRepository.findSlideShowRecommendedPasses()).thenReturn(slideShowRecommendedPasses);

        List<SlideShowPassDTO> result = passService.fetchSlideShowRecommendedPasses();

        assertEquals(0, result.size());
    }

    @Test
    void testSearchPasses() {
        List<PassInformation> searchResults = Collections.emptyList();
        SearchParameters searchParams = new SearchParameters();
        when(passRepository.findBySearchQuery(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt(), anyInt(), anyInt())).thenReturn(searchResults);

        List<PassSearchResultDTO> result = passService.searchPasses(searchParams);

        assertEquals(0, result.size());
    }
}
