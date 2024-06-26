package com.ssafy.banchic.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RefreshToken extends BaseEntity {

    @Id
    @Column(name = "refresh_token_id")
    private String id;

    @JoinColumn(name = "member_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private String keyValue;

    public void updateValue(String token) {
        this.keyValue = token;
    }

}