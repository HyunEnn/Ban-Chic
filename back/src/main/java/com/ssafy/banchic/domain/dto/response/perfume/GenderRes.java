package com.ssafy.banchic.domain.dto.response.perfume;

import com.ssafy.banchic.domain.entity.perfume.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GenderRes {

    private int female;
    private int moreFemale;
    private int unisex;
    private int moreMale;
    private int male;

    public static GenderRes from(Gender gender) {
        return GenderRes.builder()
            .female(gender.getFemale())
            .moreFemale(gender.getMoreFemale())
            .unisex(gender.getUnisex())
            .moreMale(gender.getMoreMale())
            .male(gender.getMale())
            .build();
    }

}
