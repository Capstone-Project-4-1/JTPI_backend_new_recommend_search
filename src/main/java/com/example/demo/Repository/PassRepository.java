package com.example.demo.Repository;

import com.example.demo.SearchParameters;
import com.example.demo.entity.PassInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassRepository extends JpaRepository<PassInformation, Integer>, PassRepositoryCustom {


    //신규 슬라이드용
    @Query(value = "SELECT * FROM PassInformation ORDER BY create_at DESC LIMIT 4", nativeQuery = true)
    List<PassInformation> findSlideShowNewPasses();

    //추천 슬라이드용
    @Query(value = "SELECT pi.* FROM RecommendedPass rp " +
            "JOIN PassInformation pi ON rp.recommendedPassID = pi.passID " +
            "ORDER BY RAND()" +
            "LIMIT 4", nativeQuery = true)
    List<PassInformation> findSlideShowRecommendedPasses();


    //검색
   /* @Query("SELECT p FROM PassInformation p WHERE " +
            "(:query IS NULL OR p.title LIKE %:query% OR p.cityNames LIKE %:query%) AND " +
            "(:departureCity IS NULL OR p.cityNames LIKE %:departureCity%) AND " +
            "(:arrivalCity IS NULL OR p.cityNames LIKE %:arrivalCity%) AND " +
            "(:transportType IS NULL OR p.transportType = :transportType) AND " +
            "(:cityNames IS NULL OR p.cityNames LIKE %:cityNames%) AND " +
            "(:duration IS NULL OR p.period = :duration) AND " +
            "(:quantityAdults IS NULL OR p.quantityAdults = :quantityAdults) AND " +
            "(:quantityChildren IS NULL OR p.quantityChildren = :quantityChildren)")
    List<PassInformation> findBySearchQuery(
            @Param("query") String query,
            @Param("departureCity") String departureCity,
            @Param("arrivalCity") String arrivalCity,
            @Param("transportType") String transportType,
            @Param("cityNames") String cityNames,
            @Param("duration") Integer duration,
            @Param("quantityAdults") Integer quantityAdults,
            @Param("quantityChildren") Integer quantityChildren
    );*/
    }
