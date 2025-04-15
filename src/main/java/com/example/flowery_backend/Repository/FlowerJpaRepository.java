package com.example.flowery_backend.Repository;

import com.example.flowery_backend.model.Entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerJpaRepository extends JpaRepository<Flower, Long> {


    /**
     * 꽃 정보를 이름으로 조회하는 메서드입니다.
     *
     * @param flowNm 꽃 이름
     * @return List<Flower> 꽃 정보
     */
    List<Flower> findByFlowNm(String flowNm);

    /**
     * 꽃 정보를 월로 조회하는 메서드입니다.
     *
     * @param fMonth 꽃 월
     * @return List<Flower> 꽃 정보
     */
    List<Flower> findByfMonth(String fMonth);

    /**
     * 꽃 정보를 일로 조회하는 메서드입니다.
     *
     * @param fDay 꽃 일
     * @return List<Flower> 꽃 정보
     */
    List<Flower> findByfDay(String fDay);

}
