package com.example.demo.Repository;

import com.example.demo.entity.PassInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<PassInformation, Integer> {
    @Query(value = "SELECT passID, siteID, title, description, price, period, transportType, create_at, update_at, imageURL " +
            "FROM PassInformation\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 2;", nativeQuery = true)
    List<PassInformation> findRecommendedPasses();
}
