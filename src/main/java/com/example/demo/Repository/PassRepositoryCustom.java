package com.example.demo.Repository;

import com.example.demo.entity.PassInformation;

import java.util.List;

public interface PassRepositoryCustom {
    List<PassInformation> findByDynamicQuery(
            String searchquery,
            String departureCity,
            String arrivalCity,
            String transportType,
            String cityNames,
            Integer duration
    );
}
