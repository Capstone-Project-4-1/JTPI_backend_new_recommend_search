package com.example.demo.unit;

import com.example.demo.Repository.PassRepository;
import com.example.demo.entity.PassInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class PassRepositoryTest {

    @Autowired
    private PassRepository passRepository;

    @Test
    public void testFindRecommendedPasses() {
        // 테스트 실행
        List<PassInformation> recommendedPasses = passRepository.findRecommendedPasses();

        // 검증
        assertThat(recommendedPasses).hasSize(2);
    }
}
