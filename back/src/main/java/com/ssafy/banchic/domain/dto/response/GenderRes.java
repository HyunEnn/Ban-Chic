package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Perfume;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenderRes {

    private int perfumeId;
    private String originName;
    private String brand;
    private String accord;
    private String korName;
    private String perfumeImg;

    public static GenderRes from(Perfume perfume) {
        return GenderRes.builder()
                .perfumeId(perfume.getId())
                .originName(perfume.getPerfumeName())
                .brand(perfume.getBrandName())
                .accord(perfume.getAccords())
                .korName(perfume.getKoreanName())
                .perfumeImg(perfume.getPerfumeImg())
                .build();
    }
}
