package com.example.demo.unit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.demo.entity.SiteList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

/*    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }*/
@Test
public void testFetchRecommendedPasses() {
    // Mock 데이터 설정
    SiteList site = new SiteList();
    site.setSiteID(1);
    site.setSiteName("Site1");
    PassInformation passInformation = new PassInformation();
    passInformation.setpassID(1);
    passInformation.setSite(site);
    passInformation.setTitle("Pass1");
    passInformation.setDescription("Description1");
    passInformation.setPrice(10000);
    passInformation.setPeriod(7);
    passInformation.setTransportType("TransportType1");
    passInformation.setCityNames("City1");
    passInformation.setCreateAt(LocalDateTime.now());
    passInformation.setUpdateAt(LocalDateTime.now());
    passInformation.setImageURL("image1.jpg");

    // Mock Repository 메서드 설정
    when(passRepository.findRecommendedPasses()).thenReturn(List.of(passInformation));

    // 서비스 메서드 호출
    List<PassPreviewDTO> result = passService.fetchRecommendedPasses();

    // 결과 디버깅
    System.out.println("Fetched recommended passes: " + passRepository.findRecommendedPasses());
    System.out.println("Service result: " + result);

    // 결과 확인
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getpassID()); // passID를 1로 설정
    assertEquals("Pass1", result.get(0).getTitle());
    assertEquals("image1.jpg", result.get(0).getImageURL());
}


    @Test
    public void testFetchNewPasses() {
        // Mock 데이터 설정
        SiteList site = new SiteList();
        site.setSiteID(1);
        site.setSiteName("Site1");
        PassInformation passInformation = new PassInformation();
        passInformation.setpassID(1);
        passInformation.setSite(site);
        passInformation.setTitle("Pass1");
        passInformation.setDescription("Description1");
        passInformation.setPrice(10000);
        passInformation.setPeriod(7);
        passInformation.setTransportType("TransportType1");
        passInformation.setCityNames("City1");
        passInformation.setCreateAt(LocalDateTime.now());
        passInformation.setUpdateAt(LocalDateTime.now());
        passInformation.setImageURL("image1.jpg");

        when(passRepository.findNewPasses()).thenReturn(List.of(passInformation));
        List<PassPreviewDTO> result = passService.fetchNewPasses();

        assertEquals("Pass1", result.get(0).getTitle());
    }

}
