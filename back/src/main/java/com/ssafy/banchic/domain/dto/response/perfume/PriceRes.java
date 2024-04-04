package com.ssafy.banchic.domain.dto.response.perfume;

import com.ssafy.banchic.domain.entity.perfume.Price;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PriceRes {

    private int wayOverpriced;
    private int overpriced;
    private int ok;
    private int goodValue;
    private int greatValue;

    public static PriceRes from(Price price) {
        return PriceRes.builder()
            .wayOverpriced(price.getWayOverpriced())
            .overpriced(price.getOverpriced())
            .ok(price.getOk())
            .goodValue(price.getGoodValue())
            .greatValue(price.getGreatValue())
            .build();
    }

}
