package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Review;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberReviewRes {

    private Long id;
    private int rate;
    private String content;
    private String imgUrl;
    private PerfumeOverviewRes perfumeOverviewRes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static MemberReviewRes from(Review review) {
        return MemberReviewRes.builder()
            .id(review.getId())
            .rate(review.getRate())
            .content(review.getContent())
            .imgUrl(review.getImgUrl())
            .perfumeOverviewRes(PerfumeOverviewRes.from(review.getPerfume()))
            .createdAt(review.getCreatedAt())
            .modifiedAt(review.getModifiedAt())
            .build();
    }

}
