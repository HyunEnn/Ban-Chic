package com.ssafy.banchic.oauthApi.client;

import com.ssafy.banchic.domain.type.OAuthProvider;
import com.ssafy.banchic.oauthApi.params.OAuthLogoutParams;
import com.ssafy.banchic.oauthApi.response.OAuthInfoResponse;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider(); // Client 타입 변환
    String requestAccessToken(String code); // 인증코드를 통한 엑세스 토큰 취득
    OAuthInfoResponse requestOauthInfo(String accessToken); // 엑세스토큰을 통한 프로필 정보 획득
    RevokeTokenResponseDto revokeAccessToken(OAuthLogoutParams params); // 로그아웃 시 엑세스 토큰 회수
}
