package com.ssafy.banchic.oauthApi.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RevokeTokenResponseDto {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("result")
    private String result;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

}
