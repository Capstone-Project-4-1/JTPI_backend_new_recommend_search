package com.example.demo.Repository;

import com.example.demo.entity.PassInformation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class PassRepositoryImpl implements PassRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PassInformation> findByDynamicQuery(
            String searchQuery,
            String departureCity,
            String arrivalCity,
            String transportType,
            String cityNames,
            Integer duration
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PassInformation> cq = cb.createQuery(PassInformation.class);
        Root<PassInformation> pass = cq.from(PassInformation.class);

        List<Predicate> predicates = new ArrayList<>();

        // Convert '0' to null  클라이언트에서 null값을 0으로 보냄
        if (searchQuery != null && searchQuery.equals("0")) {
            searchQuery = null;
        }
        if (departureCity != null && departureCity.equals("0")) {
            departureCity = null;
        }
        if (arrivalCity != null && arrivalCity.equals("0")) {
            arrivalCity = null;
        }
        if (transportType != null && transportType.equals("0")) {
            transportType = null;
        }
        if (cityNames != null && cityNames.equals("0")) {
            cityNames = null;
        }
        if (duration != null && duration == 0) {
            duration = null;
        }

        // Search query in title or cityNames
        if (searchQuery != null && !searchQuery.isEmpty()) {
            Predicate searchPredicate = cb.or(
                    cb.like(pass.get("title"), "%" + searchQuery + "%"),
                    cb.like(pass.get("cityNames"), "%" + searchQuery + "%")
            );
            predicates.add(searchPredicate);
        }

        // Add departureCity and arrivalCity conditions
        if (departureCity != null && !departureCity.isEmpty()) {
            predicates.add(cb.like(pass.get("stationNames"), "%" + departureCity + "%"));
        }
        if (arrivalCity != null && !arrivalCity.isEmpty()) {
            predicates.add(cb.like(pass.get("stationNames"), "%" + arrivalCity + "%"));
        }

        // Add other cityNames conditions
        if (cityNames != null && !cityNames.isEmpty()) {
            predicates.add(cb.like(pass.get("cityNames"), "%" + cityNames + "%"));
        }

        // Other predicates for transportType
        if (transportType != null) {
            if (transportType.equals("1")) {  // 전철, 버스 혼합
                predicates.add(cb.equal(pass.get("transportType"), "전철, 버스"));
            } else if (transportType.equals("2")) {  // 전철 + 전철, 버스
                predicates.add(cb.or(
                        cb.equal(pass.get("transportType"), "전철"),
                        cb.like(pass.get("transportType"), "%전철, 버스%")
                ));
            } else if (transportType.equals("3")) {  // 버스 + 전철, 버스
                predicates.add(cb.or(
                        cb.equal(pass.get("transportType"), "버스"),
                        cb.like(pass.get("transportType"), "%전철, 버스%")
                ));
            }
        }

        // Handle duration
        if (duration != null) {
            if (duration == 1) {
                predicates.add(cb.equal(pass.get("period"), 1));
            } else if (duration == 2) {
                predicates.add(cb.equal(pass.get("period"), 2));
            } else if (duration >= 3) {
                predicates.add(cb.greaterThanOrEqualTo(pass.get("period"), 3));
            }
        }

        // Combine all predicates with AND
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Log generated query
        String generatedQuery = entityManager.createQuery(cq).unwrap(org.hibernate.query.Query.class).getQueryString();
        System.out.println("Generated Query: " + generatedQuery);

        // Execute query
        TypedQuery<PassInformation> query = entityManager.createQuery(cq);
        List<PassInformation> results = query.getResultList();

        // Log query results
        System.out.println("Query Results: " + results);

        return results;
    }
}