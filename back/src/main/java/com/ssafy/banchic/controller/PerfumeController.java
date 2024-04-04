package com.ssafy.banchic.controller;

import com.ssafy.banchic.domain.dto.request.ReviewReq;
import com.ssafy.banchic.domain.dto.response.CommonResponse;
import com.ssafy.banchic.service.PerfumeReviewService;
import com.ssafy.banchic.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Perfume", description = "향수 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;
    private final PerfumeReviewService perfumeReviewService;

    @Operation(
            summary = "향수 정보 조회",
            description = "향수 정보 조회를 합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "향수 정보 조회가 완료되었습니다."
    )
    @GetMapping("/{perfumeId}")
    public ResponseEntity<CommonResponse> getPerfume(@PathVariable Integer perfumeId) {
        return ResponseEntity.ok(CommonResponse.builder()
                .message("향수 정보 조회")
                .data(perfumeService.getPerfume(perfumeId))
                .build());
    }

    @Operation(
            summary = "향수 리뷰 조회",
            description = "향수에 대한 리뷰를 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "향수 리뷰 조회가 완료되었습니다."
    )
    @GetMapping("/{perfumeId}/reviews")
    public ResponseEntity<CommonResponse> getReviews(
        @PathVariable Integer perfumeId,
        @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        return ResponseEntity.ok(CommonResponse.builder()
            .message("리뷰 목록 조회")
            .data(perfumeReviewService.getList(perfumeId, pageable))
            .build());
    }

    @Operation(
            summary = "향수 리뷰 작성",
            description = "향수에 대한 리뷰를 작성합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "향수에 대한 리뷰 작성이 성공하였습니다."
    )
    @PostMapping(value = "/{perfumeId}/reviews")
    public ResponseEntity<CommonResponse> createReview(
        @PathVariable Integer perfumeId,
        @RequestPart(value = "form") ReviewReq reviewReq,
        @RequestPart(value = "file", required = false) MultipartFile file,
        HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(CommonResponse.builder()
            .message("리뷰 작성 완료")
            .data(perfumeReviewService.create(perfumeId, reviewReq, file, httpServletRequest))
            .build());
    }

    @Operation(
            summary = "향수 리뷰 수정",
            description = "향수에 대한 리뷰를 수정합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "향수에 대한 리뷰 수정이 성공하였습니다."
    )
    @PutMapping(value = "/{perfumeId}/reviews/{reviewId}")
    public ResponseEntity<CommonResponse> updateReview(
        @PathVariable Integer perfumeId,
        @PathVariable Long reviewId,
        @RequestBody ReviewReq reviewReq,
        HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(CommonResponse.builder()
            .message("리뷰 수정 완료")
            .data(perfumeReviewService.update(perfumeId, reviewId, reviewReq, httpServletRequest))
            .build());
    }

    @Operation(
            summary = "향수 리뷰 삭제",
            description = "향수 리뷰에 대해서 삭제합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "향수에 대한 리뷰 삭제에 성공하였습니다."
    )
    @DeleteMapping(value = "/{perfumeId}/reviews/{reviewId}")
    public ResponseEntity<CommonResponse> deleteReview(
        @PathVariable Integer perfumeId,
        @PathVariable Long reviewId,
        HttpServletRequest httpServletRequest) {
        perfumeReviewService.delete(perfumeId, reviewId, httpServletRequest);
        return ResponseEntity.ok(CommonResponse.builder()
            .message("리뷰 삭제 완료")
            .build());
    }

}
