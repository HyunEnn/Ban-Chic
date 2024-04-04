package com.ssafy.banchic.domain.dto.response.perfume;

import com.ssafy.banchic.domain.entity.perfume.Longevity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LongevityRes {

    private int intimate;
    private int moderate;
    private int strong;
    private int enormous;

    public static LongevityRes from(Longevity longevity) {
        return LongevityRes.builder()
            .intimate(longevity.getIntimate())
            .moderate(longevity.getModerate())
            .strong(longevity.getStrong())
            .enormous(longevity.getEnormous())
            .build();
    }

}
