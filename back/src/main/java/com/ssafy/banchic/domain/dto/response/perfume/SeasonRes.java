package com.ssafy.banchic.domain.dto.response.perfume;

import com.ssafy.banchic.domain.entity.perfume.Season;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SeasonRes {

    private float spring;
    private float summer;
    private float fall;
    private float winter;
    private float day;
    private float night;

    public static SeasonRes from(Season season) {
        return SeasonRes.builder()
            .spring(season.getSpring())
            .summer(season.getSummer())
            .fall(season.getFall())
            .winter(season.getWinter())
            .day(season.getDay())
            .build();
    }

}
