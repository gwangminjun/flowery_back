package com.example.flowery_backend.Repository;

import com.example.flowery_backend.model.Entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerJpaRepository extends JpaRepository<Flower, Long> {


    List<Flower> findByFlowNm(String flowNm);

    List<Flower> findByfMonth(String fMonth);

    List<Flower> findByfDay(String fDay);

    @Query("""
                SELECT f FROM Flower f
                WHERE (:flowNm IS NULL OR f.flowNm = :flowNm)
                  AND (:fMonth IS NULL OR f.fMonth = :fMonth)
                  AND (:fDay IS NULL OR f.fDay = :fDay)
            """)
    List<Flower> searchFlower(
            @Param("flowNm") String flowNm,
            @Param("fMonth") String fMonth,
            @Param("fDay") String fDay
    );

}
