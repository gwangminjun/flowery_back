package com.example.flowery_backend.Repository;

import com.example.flowery_backend.model.Entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerJpaRepository extends JpaRepository<Flower, Long> {

    @Query("""
                SELECT DISTINCT f FROM Flower f
                LEFT JOIN FETCH f.hashtags h
                WHERE (:flowNm IS NULL OR :flowNm = '' OR f.flowNm LIKE %:flowNm%)
                  AND (:tagName IS NULL OR :tagName = '' OR h.tagName LIKE %:tagName%)
                  AND (:fMonth IS NULL OR :fMonth = '' OR f.fMonth = :fMonth)
                  AND (:fDay IS NULL OR :fDay = '' OR f.fDay = :fDay)
            """)
    List<Flower> searchFlowerWithFilters(
            @Param("flowNm") String flowNm,
            @Param("fMonth") String fMonth,
            @Param("fDay") String fDay,
            @Param("tagName") String tagName
    );


}
