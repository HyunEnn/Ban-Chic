package com.ssafy.banchic.oauthApi.response;


import com.ssafy.banchic.domain.type.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    String getGender();
    OAuthProvider getOAuthProvider();
//    String getAccessToken();
//    String getRefreshToken();
}
