package com.example.flowery_backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerRequest {
    private String flowNm;
    private String fMonth;
    private String fDay;
    private String tagName; // 해시태그 이름
}
