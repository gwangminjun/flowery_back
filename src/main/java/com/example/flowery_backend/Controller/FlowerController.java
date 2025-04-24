package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.CommmonApiService;
import com.example.flowery_backend.Service.FlowerService;
import com.example.flowery_backend.model.request.FlowerRequest;
import com.example.flowery_backend.model.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/flower")
@Controller
public class FlowerController extends CommmonApiService {
    private final FlowerService flowerService;


    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/getFlowerList")
    public ResponseEntity<Response> getList() {
        return createResponse(
                flowerService.getFlowers(),
                "조회 성공",
                "꽃이 없습니다."
        );
    }

    @PostMapping("/getFlower")
    public ResponseEntity<Response> getFlowerByParams(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByParams(request.getFlowNm(), request.getFMonth(), request.getFDay()),
                "조회 성공",
                "해당 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByHashtag")
    public ResponseEntity<Response> getFlowerByHashtag(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByHashtag(request.getTagName()),
                "조회 성공",
                "해당 꽃이 없습니다."
        );
    }
}
