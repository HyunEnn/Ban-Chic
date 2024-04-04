package com.ssafy.banchic.controller;

import com.ssafy.banchic.domain.dto.request.PersuitReq;
import com.ssafy.banchic.domain.dto.request.UpdateNicknameReq;
import com.ssafy.banchic.domain.dto.response.CommonResponse;
import com.ssafy.banchic.domain.dto.response.MemberInfoRes;
import com.ssafy.banchic.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

@Tag(name = "Member", description = "Member 관련 API")
@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "본인이 좋아요한 향수 목록 조회",
            description = "멤버가 좋아요한 향수 목록에 대한 조회"
    )
    @ApiResponse(
            responseCode = "200",
            description = "향수 목록이 정상적으로 조회됬습니다."
    )
    @GetMapping("/{memberId}/hearts")
    public ResponseEntity<CommonResponse> getMemberHeart(@PathVariable("memberId") Long memberId,
                                                         HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(CommonResponse.builder()
                .message("멤버가 좋아요한 향수 목록 조회")
                .data(memberService.getMemberHeart(memberId, httpServletRequest))
                .build());
    }

    @Operation(
            summary = "본인이 쓴 리뷰 목록 조회",
            description = "본인이 작성한 리뷰에 대한 목록을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "리뷰 목록이 정상적으로 조회됬습니다."
    )
    @GetMapping("/{memberId}/reviews")
    public ResponseEntity<CommonResponse> getMemberReview(@PathVariable("memberId") Long memberId,
                                                          HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(CommonResponse.builder()
                .message("멤버가 작성한 리뷰 목록 조회")
                .data(memberService.getMemberReview(memberId, httpServletRequest))
                .build());
    }

    @Operation(
            summary = "본인 정보 조회",
            description = "본인의 정보에 대해서 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "본인 정보 조회에 성공하였습니다."
    )
    @GetMapping("/{memberId}/info")
    public ResponseEntity<CommonResponse> getMemberInfo(
            @PathVariable("memberId") Long memberId, HttpServletRequest httpServletRequest) {
        MemberInfoRes memberInfoResDto = memberService.getMemberInfo(memberId, httpServletRequest);
        return new ResponseEntity<>(CommonResponse.builder()
                .message("유저 정보 조회 성공")
                .data(memberInfoResDto)
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "멤버 삭제",
            description = "본인 정보를 삭제합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "본인 정보 삭제에 성공하였습니다."
    )
    @DeleteMapping("/{memberId}")
    public ResponseEntity<CommonResponse> delete(@PathVariable("memberId") Long memberId, HttpServletRequest httpServletRequest) {
        memberService.delete(memberId, httpServletRequest);
        return new ResponseEntity<>(CommonResponse.builder()
                .message("유저 삭제 완료")
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "닉네임 변경",
            description = "멤버의 닉네임을 변경합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "닉네임 변경에 성공하였습니다."
    )
    @PutMapping("/{memberId}/nickname")
    public ResponseEntity<CommonResponse> updateNickname(
            @PathVariable("memberId") Long memberId, @RequestBody UpdateNicknameReq updateNicknameReq
            , HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(CommonResponse.builder()
                .message("닉네임 수정 완료")
                .data(memberService.updateNickname(memberId, updateNicknameReq, httpServletRequest))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "프로필 이미지 수정",
            description = "프로필 이미지를 변경합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로필 변경에 성공하였습니다."
    )
    @PutMapping("/{memberId}/image")
    public ResponseEntity<CommonResponse> updateProfileImage(
            @PathVariable("memberId") Long memberId,
            @RequestPart(value = "file", required = false) MultipartFile file,
            HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(CommonResponse.builder()
                .message("프로필 이미지 수정 완료")
                .data(memberService.updateImage(memberId, file, httpServletRequest))
                .build(), HttpStatus.OK);
    }

    @Operation(
        summary = "서베이 제출(향수 추천)",
        description = "서베이를 기반으로 향수를 추천합니다."
    )
    @ApiResponse(
        responseCode = "200",
        description = "추천 목록 조회에 성공했습니다."
    )
    @PostMapping("/survey")
    public ResponseEntity<CommonResponse> survey (
        @RequestBody PersuitReq persuitReq, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(CommonResponse.builder()
            .message("설문 제출 완료")
            .data(memberService.survey(persuitReq, httpServletRequest))
            .build(), HttpStatus.OK);
    }

    @Operation(
        summary = "추천받은 향수 목록 조회",
        description = "서베이로 추천받은 향수의 목록을 조회합니다."
    )
    @ApiResponse(
        responseCode = "200",
        description = "추천받은 향수 목록 조회에 성공했습니다."
    )
    @GetMapping("/recommend")
    public ResponseEntity<CommonResponse> getRecommendList (HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(CommonResponse.builder()
            .message("추천받은 향수 목록 조회 완료")
            .data(memberService.getRecommList(httpServletRequest))
            .build(), HttpStatus.OK);
    }

    @Operation(
        summary = "추구미 타입 조회",
        description = "멤버가 선택한 추구미 타입을 조회합니다."
    )
    @ApiResponse(
        responseCode = "200",
        description = "추구미 타입 조회에 성공했습니다."
    )
    @GetMapping("/persuit")
    public ResponseEntity<CommonResponse> getPersuit (HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(CommonResponse.builder()
            .message("추구미 타입 조회 완료")
            .data(memberService.getPersuit(httpServletRequest))
            .build(), HttpStatus.OK);
    }

    @Operation(
        summary = "이미지 기반 향수 추천",
        description = "사용자로부터 이미지를 받아 향수와 패션 스타일을 추천합니다."
    )
    @ApiResponse(
        responseCode = "200",
        description = "이미지 기반 추천에 성공하였습니다."
    )
    @PostMapping("/recommend/image")
    public ResponseEntity<CommonResponse> recommendByImage(
        @RequestPart(value = "file") MultipartFile file) {
        return new ResponseEntity<>(CommonResponse.builder()
            .message("이미지 기반 추천에 성공하였습니다.")
            .data(memberService.recommendByImage(file))
            .build(), HttpStatus.OK);
    }

    @Operation(
        summary = "CF 알고리즘을 통한 향수 추천",
        description = "CF 알고리즘을 통해 향수를 추천합니다."
    )
    @ApiResponse(
        responseCode = "200",
        description = "추천 목록 조회에 성공했습니다."
    )
    @PostMapping("/recommend/cf")
    public ResponseEntity<CommonResponse> recommendByCf (HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(CommonResponse.builder()
            .message("추천 목록 조회 성공")
            .data(memberService.recommendByCf(httpServletRequest))
            .build(), HttpStatus.OK);
    }

}
