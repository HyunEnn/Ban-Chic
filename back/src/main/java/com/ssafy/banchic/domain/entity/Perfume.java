package com.ssafy.banchic.domain.entity;

import com.ssafy.banchic.domain.entity.perfume.*;
import lombok.*;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Perfume extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfume_id")
    private Integer id;

    @Column(name = "perfume_name")
    private String perfumeName;
    private String perfumeImg;
    private String brandName;
    private String brandImg;
    private String accords;
    @Column(length = 5000)
    private String notes;
    private int year;
    private int bestRate;
    private int vote;
    private float rate;
    private String koreanName;

    // 향수 조회수
    @Builder.Default
    private int perfumeCnt = 0;
    // 향수 좋아요수
    @Builder.Default
    private int heartCnt = 0;

    @OneToOne
    @JoinColumn(name = "sillage_id")
    private Sillage sillage;

    @OneToOne
    @JoinColumn(name = "logevity_id")
    private Longevity longevity;

    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;

    @OneToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToOne
    @JoinColumn(name = "likeability_id")
    private Likeability likeability;

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    // 메서드 정리
    public void increaseHeart() {
        this.heartCnt++;
    }

    public void decreaseHeartCnt() {
        if(this.heartCnt > 0)
            this.heartCnt--;
    }
}
