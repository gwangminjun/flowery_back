package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.CommmonApiService;
import com.example.flowery_backend.Service.FlowerHashtagService;
import com.example.flowery_backend.model.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/api/flowerHashTag")
@Controller
public class FlowerHashtagController extends CommmonApiService {
    private final FlowerHashtagService flowerHashtagService;

    public FlowerHashtagController(FlowerHashtagService flowerHashtagService) {
        this.flowerHashtagService = flowerHashtagService;
    }

    @GetMapping("/getFlowerHashtag")
    @ResponseBody
    public ResponseEntity<Response> getFlowerHashtag() {

        return createResponse(
                flowerHashtagService.getFlowerHashtag(),
                "조회 성공",
                "해시태그가 없습니다."
        );
    }

}
