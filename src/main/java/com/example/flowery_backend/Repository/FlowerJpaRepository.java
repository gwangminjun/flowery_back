package com.example.flowery_backend.Repository;

import com.example.flowery_backend.model.Entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerJpaRepository extends JpaRepository<Flower, Long> {

    /**
     * 꽃 정보를 이름, 월, 일로 조회하는 메서드입니다.
     *
     * @param flowNm 꽃 이름
     * @param fMonth 꽃 월
     * @param fDay   꽃 일
     * @return List<Flower> 꽃 정보
     */
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

    /**
     * 해시태그로 꽃 정보를 조회하는 메서드입니다.
     *
     * @param tagName 해시태그 이름
     * @return List<Flower> 꽃 정보
     */
    @Query("SELECT f FROM Flower f JOIN f.hashtags h WHERE h.tagName = :tagName")
    List<Flower> findByHashtag(@Param("tagName") String tagName);


    // FlowerJpaRepository.java
    @Query("SELECT DISTINCT f FROM Flower f LEFT JOIN FETCH f.hashtags")
    List<Flower> findAllWithHashtags();


}
