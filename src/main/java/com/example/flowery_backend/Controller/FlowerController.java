package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.CommmonApiService;
import com.example.flowery_backend.Service.FlowerService;
import com.example.flowery_backend.model.request.FlowerRequest;
import com.example.flowery_backend.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "전체 꽃 목록 조회",
            description = "시스템에 등록된 모든 꽃 정보를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "꽃이 없습니다.",
                    content = @Content
            )
    })
    @GetMapping("/getFlowerList")
    public ResponseEntity<Response> getList() {
        return createResponse(
                flowerService.getFlowers(),
                "조회 성공",
                "꽃이 없습니다."
        );
    }


    @Operation(
            summary = "특정 꽃 정보 조회",
            description = "꽃 이름, 월, 일을 기준으로 특정 꽃의 정보를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "해당 꽃이 없습니다.",
                    content = @Content
            )
    })
    @PostMapping("/getFlower")
    public ResponseEntity<Response> getFlowerByParams(@RequestBody FlowerRequest request) {
        return createResponse(
                flowerService.getFlowerByParams(request.getFlowNm(), request.getFMonth(), request.getFDay()),
                "조회 성공",
                "해당 꽃이 없습니다."
        );
    }

//    @PostMapping("/getFlowerByHashtag")
//    public ResponseEntity<Response> getFlowerByHashtag(@RequestBody FlowerRequest request) {
//        return createResponse(
//                flowerService.getFlowerByHashtag(request.getTagName()),
//                "조회 성공",
//                "해당 꽃이 없습니다."
//        );
//    }
}
