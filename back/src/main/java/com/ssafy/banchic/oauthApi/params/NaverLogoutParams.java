package com.ssafy.banchic.oauthApi.params;

import com.ssafy.banchic.domain.type.OAuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Getter
@NoArgsConstructor
public class NaverLogoutParams implements OAuthLogoutParams {
    private String accessToken;
    private String serviceProvider;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }

    @Override
    public MultiValueMap<String, String> makebody() {
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("access_token", accessToken);
        body.add("service_provider", serviceProvider);
        return body;
    }
}
