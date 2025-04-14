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

    @PostMapping("/getFlowerByNm")
    public ResponseEntity<Response> getFlowerByNm(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByNm(request.getFlowNm()),
                "조회 성공",
                "해당 이름의 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByMonth")
    public ResponseEntity<Response> getFlowerByMonth(@RequestBody FlowerRequest request) {
        System.out.println(">>> 받은 월: " + request.getFMonth());
        return createResponse(
                flowerService.getFlowerByMonth(request.getFMonth()),
                "조회 성공",
                "해당 월의 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByDay")
    public ResponseEntity<Response> getFlowerByDay(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByDay(request.getFDay()),
                "조회 성공",
                "해당 일의 꽃이 없습니다."
        );
    }

}
