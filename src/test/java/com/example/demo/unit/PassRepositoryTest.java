package com.example.demo.unit;
/*

import com.example.demo.Repository.PassRepository;
import com.example.demo.entity.PassInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PassRepositoryTest {

    @Autowired
    private PassRepository passRepository;


    @Test
    void testFindSlideShowNewPasses() {
        List<PassInformation> slideShowNewPasses = passRepository.findSlideShowNewPasses();
        assertNotNull(slideShowNewPasses);
    }


    @Test
    void testFindSlideShowRecommendedPasses() {
        List<PassInformation> slideShowRecommendedPasses = passRepository.findSlideShowRecommendedPasses();
        assertNotNull(slideShowRecommendedPasses);
        assertTrue(slideShowRecommendedPasses.size() <= 4);
    }

    @Test
    void testFindBySearchQuery() {
        String query = "%test%";
        String departureCity = "Seoul";
        String arrivalCity = "Busan";
        String transportType = "Train";
        String cityNames = "Seoul";
        Integer duration = 5;
        Integer quantityAdults = 2;
        Integer quantityChildren = 1;

        List<PassInformation> searchResults = passRepository.findBySearchQuery(
                query, departureCity, arrivalCity, transportType, cityNames, duration, quantityAdults, quantityChildren);

        assertNotNull(searchResults);
    }
}
*/
