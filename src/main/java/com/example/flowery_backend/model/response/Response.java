package com.example.flowery_backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Response<T> {
    private String resultMsg;
    private int totalCount;
    private List<T> data;
}

