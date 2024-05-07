package com.example.demo.unit;
import com.example.demo.Controller.PassController;
import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.Service.PassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
        PassPreviewDTO dto = new PassPreviewDTO();
        dto.setpassId(1);
        dto.setTitle("Test Pass");
        dto.setImageURL("http://example.com/image.jpg");

        given(passService.fetchRecommendedPasses()).willReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/passes/recommended"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].title").value(dto.getTitle()))
                .andExpect(jsonPath("$[0].imageURL").value(dto.getImageURL()));
    }
}
