package com.example.flowery_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flowery_backend.model.Flower;

import java.util.Optional;

@Repository
public interface FlowerJpaRepository extends JpaRepository<Flower, Long> {
    Optional<Flower> findByFlowNm(String flowNm);


}
