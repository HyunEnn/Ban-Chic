package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SeasonRes {

    private int perfumeId;
    private String originName;
    private String brand;
    private String accord;
    private String korName;
    private String perfumeImg;

    public static SeasonRes from(Perfume perfume) {
        return SeasonRes.builder()
                .accord(perfume.getAccords())
                .originName(perfume.getPerfumeName())
                .brand(perfume.getBrandName())
                .perfumeId(perfume.getId())
                .perfumeImg(perfume.getPerfumeImg())
                .korName(perfume.getKoreanName())
                .build();
    }
}

// accord, brand, id, perfumeName, perfumeKoreanName, perfumeImg
