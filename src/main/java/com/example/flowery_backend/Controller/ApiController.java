package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.FlowerHashtagService;
import com.example.flowery_backend.Service.FlowerService;
import com.example.flowery_backend.model.Entity.Flower;
import com.example.flowery_backend.model.Entity.FlowerHashtag;
import com.example.flowery_backend.model.request.FlowerRequest;
import com.example.flowery_backend.model.response.FlowerListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@Controller
public class ApiController {
    private final FlowerService flowerService;

    private final FlowerHashtagService flowerHashtagService;

    public ApiController(FlowerService flowerService, FlowerHashtagService flowerHashtagService) {
        this.flowerHashtagService = flowerHashtagService;
        this.flowerService = flowerService;
    }

    @GetMapping("/getFlowerList")
    public ResponseEntity<FlowerListResponse> getList() {
        List<Flower> flowers = flowerService.getFlowers();

        FlowerListResponse response = new FlowerListResponse(
                "접속 성공",                   // resultMsg
                flowers.size(),               // totalCount
                flowers                      // flowers 리스트
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFlowerHashtag")
    @ResponseBody
    public List<FlowerHashtag> getFlowerHashtag() {

        return flowerHashtagService.getFlowerHashtag();
    }

    @PostMapping("/getFlowerByNm")
    public ResponseEntity<FlowerListResponse> getFlowerByNm(@RequestBody FlowerRequest request) {
        return createFlowerResponse(
                flowerService.getFlowerByNm(request.getFlowNm()),
                "조회 성공",
                "해당 이름의 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByMonth")
    public ResponseEntity<FlowerListResponse> getFlowerByMonth(@RequestBody FlowerRequest request) {
        System.out.println(">>> 받은 월: " + request.getFMonth());
        return createFlowerResponse(
                flowerService.getFlowerByMonth(request.getFMonth()),
                "조회 성공",
                "해당 월의 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByDay")
    public ResponseEntity<FlowerListResponse> getFlowerByDay(@RequestBody FlowerRequest request) {
        return createFlowerResponse(
                flowerService.getFlowerByDay(request.getFDay()),
                "조회 성공",
                "해당 일의 꽃이 없습니다."
        );
    }


    private ResponseEntity<FlowerListResponse> createFlowerResponse(List<Flower> flowers, String successMsg, String notFoundMsg) {
        if (!flowers.isEmpty()) {
            return ResponseEntity.ok(
                    new FlowerListResponse(successMsg, flowers.size(), flowers)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new FlowerListResponse(notFoundMsg, 0, List.of())
            );
        }
    }

}
