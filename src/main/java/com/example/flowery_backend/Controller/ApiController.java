package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.FlowerHashtagService;
import com.example.flowery_backend.Service.FlowerService;
import com.example.flowery_backend.model.request.FlowerRequest;
import com.example.flowery_backend.model.response.Response;
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
    public ResponseEntity<Response> getList() {
        return createResponse(
                flowerService.getFlowers(),
                "조회 성공",
                "꽃이 없습니다."
        );
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


    private <T> ResponseEntity<Response> createResponse(List<T> data, String successMsg, String notFoundMsg) {
        if (!data.isEmpty()) {
            return ResponseEntity.ok(
                    new Response<>(successMsg, data.size(), data)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response<>(notFoundMsg, 0, List.of())
            );
        }
    }

}
