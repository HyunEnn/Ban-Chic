package com.ssafy.banchic.controller;

import com.ssafy.banchic.domain.dto.response.CommonResponse;
import com.ssafy.banchic.domain.type.OAuthProvider;
import com.ssafy.banchic.oauthApi.params.NaverLogoutParams;
import com.ssafy.banchic.service.OAuthLoginService;
import com.ssafy.banchic.service.OAuthLogoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Auth API", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;
    private final OAuthLogoutService oAuthLogoutService;
    private final RestTemplate restTemplate;

    @Value("${oauth.kakao.url.logout}")
    private String kakaoLogoutUrl;
    @Value("${oauth.kakao.client-id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.url.redirect}")
    private String kakaoLogoutRedirectUrl;

    @Operation(
            summary = "카카오 로그인",
            description = "카카오 로그인을 합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "카카오 로그인에 성공하였습니다."
    )
    @GetMapping("/login/kakao")
    public ResponseEntity<CommonResponse> loginKakao(
        @RequestParam("code") String code, HttpServletResponse response) {
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK)
                .message("카카오 로그인 성공")
                .data(oAuthLoginService.login(code, OAuthProvider.KAKAO, response))
                .build(), HttpStatus.OK);
    }

    @Operation(
            summary = "네이버 로그인",
            description = "네이버 로그인을 합니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "네이버 로그인에 성공하였습니다."
    )
    @GetMapping("/login/naver")
    public ResponseEntity<CommonResponse> loginNaver(
        @RequestParam("code") String code, HttpServletResponse response) {
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK)
                .message("네이버 로그인 성공")
                .data(oAuthLoginService.login(code, OAuthProvider.NAVER, response))
                .build(), HttpStatus.OK);
    }

    /**
     * access token -> 소셜 로그인의 access를 지우는 방식
     * 로그아웃 하면 , 소셜의 access token, jwt 토큰도 둘 다 무효화
     * jwt token을 무효화 시킴
     * @param params
     * @return
     */

    @PostMapping("/logout/naver")
    public ResponseEntity<CommonResponse> logoutNaver(@RequestBody NaverLogoutParams params) {
        return new ResponseEntity<>(CommonResponse.builder()
                .message("네이버 로그아웃 성공")
                .data(oAuthLogoutService.logout(params))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/logout/kakao")
    public ResponseEntity<CommonResponse> logoutKakao(@RequestHeader("Authorization") String accessToken) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", kakaoClientId);
        params.add("logout_redirect_uri", kakaoLogoutRedirectUrl);
//        params.add("state", "your_unique_state_value"); // CSRF 방지를 위한 임의의 문자열

        // 로그아웃 URL 생성
        String url = "https://kauth.kakao.com/oauth/logout" + "?" +
                params.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue().get(0))
                        .reduce((p1, p2) -> p1 + "&" + p2)
                        .orElse("");

        // 로그아웃 요청
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.FOUND) {
            // 로그아웃이 성공적으로 수행되었을 경우
            return new ResponseEntity<>(CommonResponse.builder()
                    .message("카카오 로그아웃 성공")
                    .build(), HttpStatus.OK);
        } else {
            // 로그아웃이 실패한 경우
            return new ResponseEntity<>(CommonResponse.builder()
                    .message("카카오 로그아웃 실패")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "엑세스 토큰 재발급",
            description = "리프래쉬 토큰으로 엑세스 토큰을 재발급합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "엑세스 토큰을 재발급합니다."
    )
    @PostMapping("/renew")
    public ResponseEntity<CommonResponse> renewToken(
        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        oAuthLoginService.renewAccessToken(httpServletRequest, httpServletResponse);
        return new ResponseEntity<>(CommonResponse.builder()
            .message("액세스 토큰 갱신 완료")
            .build(), HttpStatus.OK);
    }

}
