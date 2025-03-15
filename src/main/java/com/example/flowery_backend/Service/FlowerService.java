package com.example.flowery_backend.Service;

import com.example.flowery_backend.Repository.FlowerJpaRepository;
import com.example.flowery_backend.model.Flower;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService {

    private final FlowerJpaRepository flowerJpaRepository;

    public FlowerService(FlowerJpaRepository flowerJpaRepository) {
        this.flowerJpaRepository = flowerJpaRepository;
    }

    public List<Flower> getFlowers() {
        return flowerJpaRepository.findAll();
    }

}
