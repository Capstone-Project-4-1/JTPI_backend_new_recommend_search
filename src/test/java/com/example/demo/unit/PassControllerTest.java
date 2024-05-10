package com.example.demo.unit;
import com.example.demo.Controller.PassController;
import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.Service.PassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PassController.class)
public class PassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassService passService;

    @Test
    public void testGetRecommendedPasses() throws Exception {
        PassPreviewDTO dto = new PassPreviewDTO(1,"Test Pass","http://example.com/image.jpg");


        given(passService.fetchRecommendedPasses()).willReturn(Arrays.asList(dto));

        // API 요청 및 응답 검증
        mockMvc.perform(get("/api/passes/recommended")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getpassID()))
                .andExpect(jsonPath("$[0].title").value(dto.getTitle()))
                .andExpect(jsonPath("$[0].imageURL").value(dto.getImageURL()));
    }

    @Test
    public void testGetNewPasses() throws Exception {
        // 새로운 PassPreviewDTO 객체 생성
        PassPreviewDTO dto = new PassPreviewDTO(2, "New Pass", "http://example.com/new.jpg");

        // BDD 스타일 모킹
        given(passService.fetchNewPasses()).willReturn(Arrays.asList(dto));

        // API 요청 및 응답 검증
        mockMvc.perform(get("/api/passes/new")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getpassID()))
                .andExpect(jsonPath("$[0].title").value(dto.getTitle()))
                .andExpect(jsonPath("$[0].imageURL").value(dto.getImageURL()));
    }
}

