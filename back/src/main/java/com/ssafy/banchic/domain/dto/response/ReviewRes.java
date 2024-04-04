package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Review;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewRes {

    private Long id;
    private int rate;
    private String content;
    private String imgUrl;
    private MemberInfoRes member;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ReviewRes from(Review review) {
        return ReviewRes.builder()
            .id(review.getId())
            .rate(review.getRate())
            .content(review.getContent())
            .imgUrl(review.getImgUrl())
            .member(MemberInfoRes.from(review.getMember()))
            .createdAt(review.getCreatedAt())
            .modifiedAt(review.getModifiedAt())
            .build();
    }

}
