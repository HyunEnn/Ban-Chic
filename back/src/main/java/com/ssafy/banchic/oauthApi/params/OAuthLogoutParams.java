package com.ssafy.banchic.oauthApi.params;

import com.ssafy.banchic.domain.type.OAuthProvider;
import org.springframework.util.MultiValueMap;


public interface OAuthLogoutParams {

    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makebody();

}
