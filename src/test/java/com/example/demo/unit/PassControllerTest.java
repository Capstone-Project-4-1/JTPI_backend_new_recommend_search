package com.example.demo.unit;
import com.example.demo.Controller.PassController;
import com.example.demo.DTO.PassSearchResultDTO;
import com.example.demo.DTO.SlideShowPassDTO;
import com.example.demo.SearchParameters;
import com.example.demo.Service.PassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PassController.class)
public class PassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassService passService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void testGetSlideShowRecommendedPasses() throws Exception {
        List<SlideShowPassDTO> slideShowRecommendedPasses = Collections.emptyList();
        when(passService.fetchSlideShowRecommendedPasses()).thenReturn(slideShowRecommendedPasses);

        mockMvc.perform(get("/api/passes/slideshow/recommended"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(slideShowRecommendedPasses)));
    }


    @Test
    void testGetSlideShowNewPasses() throws Exception {
        List<SlideShowPassDTO> slideShowNewPasses = Collections.emptyList();
        when(passService.fetchSlideShowNewPasses()).thenReturn(slideShowNewPasses);

        mockMvc.perform(get("/api/passes/slideshow/new"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(slideShowNewPasses)));
    }

    @Test
    void testSearchPasses() throws Exception {
        List<PassSearchResultDTO> searchResults = Collections.emptyList();
        SearchParameters searchParams = new SearchParameters();
        when(passService.searchPasses(any(SearchParameters.class))).thenReturn(searchResults);

        mockMvc.perform(post("/api/passes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchParams)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(searchResults)));
    }
}

