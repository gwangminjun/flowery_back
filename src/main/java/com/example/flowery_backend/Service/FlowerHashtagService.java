package com.example.flowery_backend.Service;

import com.example.flowery_backend.Repository.FlowerHashtagJpaRepository;
import com.example.flowery_backend.model.Entity.FlowerHashtag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerHashtagService {

    /**
     * 꽃 해시태그 정보를 저장하는 JPA 레포지토리입니다.
     */
    private final FlowerHashtagJpaRepository flowerHashtagJpaRepository;

    /**
     * 생성자 주입을 통해 FlowerHashtagJpaRepository를 주입받습니다.
     *
     * @param flowerHashtagJpaRepository FlowerHashtagJpaRepository
     */
    public FlowerHashtagService(FlowerHashtagJpaRepository flowerHashtagJpaRepository) {
        this.flowerHashtagJpaRepository = flowerHashtagJpaRepository;
    }

    /**
     * 모든 꽃 해시태그 정보를 조회하는 메서드입니다.
     *
     * @return List<FlowerHashtag> 꽃 해시태그 정보
     */
    public List<FlowerHashtag> getFlowerHashtag() {
        return flowerHashtagJpaRepository.findAll();
    }


}
