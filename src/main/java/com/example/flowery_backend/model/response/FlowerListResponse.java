package com.example.flowery_backend.model.response;

import com.example.flowery_backend.model.Flower;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FlowerListResponse {
    private String resultMsg;
    private int totalCount;
    private List<Flower> flowers;
}
