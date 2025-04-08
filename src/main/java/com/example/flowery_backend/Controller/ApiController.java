package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.FlowerService;
import com.example.flowery_backend.model.Flower;
import com.example.flowery_backend.model.request.FlowerRequest;
import com.example.flowery_backend.model.response.FlowerListResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
public class ApiController {
    private final FlowerService flowerService;

    public ApiController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/getList")
    public ResponseEntity<FlowerListResponse> getList() {
        List<Flower> flowers = flowerService.getFlowers();

        FlowerListResponse response = new FlowerListResponse(
                "접속 성공",                   // resultMsg
                flowers.size(),               // totalCount
                flowers                      // flowers 리스트
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/getFlowerByNm")
    public ResponseEntity<FlowerListResponse> getFlowerByNm(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByNm(request.getFlowNm()),
                "조회 성공",
                "해당 이름의 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByMonth")
    public ResponseEntity<FlowerListResponse> getFlowerByMonth(@RequestBody FlowerRequest request) {
        System.out.println(">>> 받은 월: " + request.getFMonth());
        return createResponse(
                flowerService.getFlowerByMonth(request.getFMonth()),
                "조회 성공",
                "해당 월의 꽃이 없습니다."
        );
    }

    @PostMapping("/getFlowerByDay")
    public ResponseEntity<FlowerListResponse> getFlowerByDay(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByDay(request.getFDay()),
                "조회 성공",
                "해당 일의 꽃이 없습니다."
        );
    }



    private ResponseEntity<FlowerListResponse> createResponse(List<Flower> flowers, String successMsg, String notFoundMsg) {
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





//    @GetMapping("/getFlower")
//    public ResponseEntity<Flower> getFlower(Long id) {
//        Flower flower = flowerService.getFlowers()id);
//        return ResponseEntity.ok(flower); // JSON 응답
//    }




}
