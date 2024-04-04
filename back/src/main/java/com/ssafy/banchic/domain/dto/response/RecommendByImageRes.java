package com.ssafy.banchic.domain.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecommendByImageRes {

    private String fashion;
    private List<PerfumeOverviewRes> perfumeOverviewResList;

}
