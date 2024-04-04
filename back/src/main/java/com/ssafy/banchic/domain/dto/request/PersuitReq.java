package com.ssafy.banchic.domain.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersuitReq {

        private Boolean clear;
        private Boolean romantic;
        private Boolean pretty;
        private Boolean coolcasual;
        private Boolean casual;
        private Boolean natural;
        private Boolean elegant;
        private Boolean dynamic;
        private Boolean wild;
        private Boolean gorgeous;
        private Boolean chic;
        private Boolean modern;
        private Boolean classic;
        private Boolean dandy;

}
