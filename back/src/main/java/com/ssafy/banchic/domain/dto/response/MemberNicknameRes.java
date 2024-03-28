package com.ssafy.banchic.domain.dto.response;

import com.ssafy.banchic.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberNicknameRes {

    private String nickname;

    public static MemberNicknameRes from(Member member) {
        return MemberNicknameRes.builder()
                .nickname(member.getNickname())
                .build();
    }

}
