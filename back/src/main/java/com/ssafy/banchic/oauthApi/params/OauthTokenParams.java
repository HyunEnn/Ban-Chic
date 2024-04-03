package com.ssafy.banchic.oauthApi.params;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OauthTokenParams {
    private String refreshToken;
}
