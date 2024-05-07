package com.example.demo.unit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.Repository.PassRepository;
import com.example.demo.Service.PassService;
import com.example.demo.entity.PassInformation;

@ExtendWith(MockitoExtension.class)  // Mockito 확장 기능 사용
public class PassServiceTest {

    @Mock
    private PassRepository passRepository;

    @InjectMocks
    private PassService passService;

    @Test
    public void testFetchRecommendedPasses() {
        // Mock 데이터 설정
        PassInformation passInformation = new PassInformation();
        passInformation.setPassId(1);

        passInformation.setTitle("Pass1");
        passInformation.setDescription("Description1");
        passInformation.setPrice(10000);
        passInformation.setPeriod(7);
        passInformation.setTransportType("TransportType1");
    /*    passInformation.setCityNames("City1");*/
        passInformation.setCreateAt(LocalDateTime.now());
        passInformation.setUpdateAt(LocalDateTime.now());
        passInformation.setImageURL("image1.jpg");

        // Mock Repository 메서드 설정
        when(passRepository.findRecommendedPasses()).thenReturn(List.of(passInformation));

        // 서비스 메서드 호출
        List<PassPreviewDTO> result = passService.fetchRecommendedPasses();

        // 결과 확인
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Pass1", result.get(0).getTitle());
        assertEquals("image1.jpg", result.get(0).getImageURL());
    }
}
