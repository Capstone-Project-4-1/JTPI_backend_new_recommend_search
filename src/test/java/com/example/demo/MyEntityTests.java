package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.PassInformation;
import com.example.demo.entity.SiteList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.example.demo.Repository.PassRepository;

@DataJpaTest
public class MyEntityTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PassRepository passRepository;

    @Test
    public void testPassInformationMapping() {
        // 임의의 SiteList 엔티티 생성
        SiteList site = new SiteList(); // SiteList 세부 구현 필요
        entityManager.persist(site);

        // PassInformation 엔티티 생성 및 저장
        PassInformation pass = new PassInformation(site, "Explorer Pass",
                "Access to multiple sites",
                120,
                30,
                "Bus, Train",
                "New York, Boston",
                LocalDateTime.now(), LocalDateTime.now(),
                "http://example.com/image.jpg");
        pass = entityManager.persistFlushFind(pass);

        // 저장된 엔티티 조회
        PassInformation found = passRepository.findById(pass.getPassId()).orElse(null);

        // 검증
        assertNotNull(found);
        assertEquals("Explorer Pass", found.getTitle());
        assertEquals("Access to multiple sites", found.getDescription());
        assertEquals(120, found.getPrice());
    }
}

