package com.example.demo.integration;
import aj.org.objectweb.asm.TypeReference;
import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.DTO.SlideShowPassDTO;
import com.example.demo.DTO.PassSearchResultDTO;
import com.example.demo.SearchParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PassControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetRecommendedPasses() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/passes/recommended"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();

        System.out.println("Response: " + response); // 디버그 로그 추가

        List<PassPreviewDTO> actualRecommendedPasses = objectMapper.readValue(response, new com.fasterxml.jackson.core.type.TypeReference<List<PassPreviewDTO>>(){});

        // 4개의 추천 패스가 반환되었는지 확인
        assertEquals(4, actualRecommendedPasses.size());

        // 각 항목이 예상된 구조를 가지고 있는지 확인
        for (PassPreviewDTO actualPass : actualRecommendedPasses) {
            assertNotNull(actualPass.getpassID(), "Pass ID should not be null");
            assertNotNull(actualPass.getTitle(), "Title should not be null");
            assertNotNull(actualPass.getImageURL(), "Image URL should not be null");
        }
    }

    @Test
    void testGetSlideShowRecommendedPasses() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/passes/recommended"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();

        System.out.println("Response: " + response); // 디버그 로그 추가

        List<PassPreviewDTO> actualRecommendedPasses = objectMapper.readValue(response, new com.fasterxml.jackson.core.type.TypeReference<List<PassPreviewDTO>>() {
        });

        // 4개의 추천 패스가 반환되었는지 확인
        assertEquals(4, actualRecommendedPasses.size());

        // 각 항목이 예상된 구조를 가지고 있는지 확인
        for (PassPreviewDTO actualPass : actualRecommendedPasses) {
            assertNotNull(actualPass.getpassID(), "Pass ID should not be null");
            assertNotNull(actualPass.getTitle(), "Title should not be null");
            assertNotNull(actualPass.getImageURL(), "Image URL should not be null");
        }
    }
    @Test
    void testGetNewPasses() throws Exception {
        // 데이터베이스에 있는 새로운 패스를 예상값으로 설정
        List<PassPreviewDTO> expectedNewPasses = List.of(
                new PassPreviewDTO(10, "Shiz", "http://example.com/image10.jpg"),
                new PassPreviewDTO(9, "Jenn", "http://example.com/image9.jpg"),
                new PassPreviewDTO(8, "Lisa", "http://example.com/image8.jpg"),
                new PassPreviewDTO(7, "Quick", "http://example.com/image7.jpg")
        );

        String response = mockMvc.perform(get("/api/passes/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + response); // 디버그 로그 추가

        mockMvc.perform(get("/api/passes/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedNewPasses)));
    }

    @Test
    void testGetSlideShowNewPasses() throws Exception {
        // 데이터베이스에 있는 슬라이드쇼 새로운 패스를 예상값으로 설정
        List<PassPreviewDTO> expectedSlideShowNewPasses = List.of(
                new PassPreviewDTO(10, "Shiz", "http://example.com/image10.jpg"),
                new PassPreviewDTO(9, "Jenn", "http://example.com/image9.jpg"),
                new PassPreviewDTO(8, "Lisa", "http://example.com/image8.jpg"),
                new PassPreviewDTO(7, "Quick", "http://example.com/image7.jpg")
        );

        String response = mockMvc.perform(get("/api/passes/slideshow/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + response); // 디버그 로그 추가

        mockMvc.perform(get("/api/passes/slideshow/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSlideShowNewPasses)));
    }

    @Test
    void testSearchPasses() throws Exception {
        SearchParameters searchParams = new SearchParameters();
        searchParams.setQuery("Test Query");
        searchParams.setDepartureCity("Test Departure");
        searchParams.setArrivalCity("Test Arrival");
        searchParams.setTransportType("Test Transport");
        searchParams.setCityNames("Test City");
        searchParams.setDuration(7);
        searchParams.setQuantityAdults(2);
        searchParams.setQuantityChildren(1);

        // 데이터베이스에 있는 검색 결과를 예상값으로 설정
        List<PassSearchResultDTO> expectedSearchResults = List.of(
                new PassSearchResultDTO(1, "Tokyo","http://example.com/image1.jpg",  "Tokyo", 100),
                new PassSearchResultDTO(2,  "Kyoto","http://example.com/image2.jpg", "Kyoto", 150)
        );

        String response = mockMvc.perform(post("/api/passes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchParams)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + response); // 디버그 로그 추가

        mockMvc.perform(post("/api/passes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchParams)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSearchResults)));
    }
}
