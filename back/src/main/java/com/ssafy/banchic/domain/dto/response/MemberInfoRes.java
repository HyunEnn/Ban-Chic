package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberInfoRes {

    private String email;
    private String image;
    private String nickname;

    public static MemberInfoRes from(Member member) {
        return MemberInfoRes.builder()
            .email(member.getEmail())
            .image(member.getImage())
            .nickname(member.getNickname())
            .build();
    }

}
