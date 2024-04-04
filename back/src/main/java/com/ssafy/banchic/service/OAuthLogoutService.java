package com.ssafy.banchic.service;

import com.ssafy.banchic.oauthApi.client.RevokeTokenResponseDto;
import com.ssafy.banchic.oauthApi.params.OAuthLogoutParams;
import com.ssafy.banchic.oauthApi.response.RequestOAuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLogoutService {

    private final RequestOAuthInfoService requestOAuthInfoService;

    public RevokeTokenResponseDto logout(OAuthLogoutParams params) {
        return requestOAuthInfoService.logoutService(params);
    }

}
