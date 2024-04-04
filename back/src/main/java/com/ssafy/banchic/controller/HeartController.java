package com.ssafy.banchic.controller;

import com.ssafy.banchic.domain.dto.response.CommonResponse;
import com.ssafy.banchic.service.HeartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Heart", description = "좋아요 관련 API")
@RestController
@Slf4j
@RequestMapping("/perfumes")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @Operation(
            summary = "좋아요 누르기",
            description = "좋아요를 누르면 +1, 해제하면 -1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "좋아요가 성공적으로 작동되었습니다."
    )
    @PostMapping("/{perfumeId}/hearts")
    public ResponseEntity<CommonResponse> update(HttpServletRequest httpServletRequest,
                                                 @PathVariable("perfumeId") Integer perfumeId) {

        /**
         * 1. jwt token의 값의 유효시간이 남아있는지 체크합니다.
         * 2. 확인된 토큰에 대해서 디코딩을 통해서 memberId의 값을 꺼내옵니다.
         * 3. 꺼내온 값을 통해서, 맴버 아이디와 향수 아이디를 통해서 좋아요 추가 및 삭제를 진행
         */
        return ResponseEntity.ok(CommonResponse.builder()
                .message("좋아요가 정상적으로 작동중입니다.")
                .data(heartService.addHeart(perfumeId, httpServletRequest))
                .build());
    }

    @Operation(
            summary = "좋아요 조회",
            description = "현재 멤버가 좋아요를 눌렀는 지 체크"
    )
    @ApiResponse(
            responseCode = "200",
            description = "좋아요를 눌렀는 지에 대한 확인되었습니다."
    )
    @GetMapping("/{perfumeId}/hearts")
    public ResponseEntity<CommonResponse> getPerfumeHeart(@PathVariable("perfumeId") Integer perfumeId, HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok(CommonResponse.builder()
                .message("향수 좋아요 조회")
                .data(heartService.checkHeart(perfumeId, httpServletRequest))
                .build());

    }

}
