package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Recommend;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RecommRes {

    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private int six;
    private int seven;
    private int eight;
    private int nine;
    private int ten;

    public static RecommRes from(Recommend entity) {
        return RecommRes.builder()
            .one(entity.getOne())
            .two(entity.getTwo())
            .three(entity.getThree())
            .four(entity.getFour())
            .five(entity.getFive())
            .six(entity.getSix())
            .seven(entity.getSeven())
            .eight(entity.getEight())
            .nine(entity.getNine())
            .ten(entity.getTen())
            .build();
    }

}
