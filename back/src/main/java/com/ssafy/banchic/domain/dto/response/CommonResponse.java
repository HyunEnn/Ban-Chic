package com.ssafy.banchic.domain.dto.response;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CommonResponse {

    @Builder.Default
    private Date timeStamp = new Date();
    @Builder.Default
    private HttpStatus status = HttpStatus.OK;
    private String message;
    private Object data;

}