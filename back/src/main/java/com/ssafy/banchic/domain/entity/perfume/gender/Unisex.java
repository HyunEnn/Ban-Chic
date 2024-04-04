package com.ssafy.banchic.domain.entity.perfume.gender;

import com.ssafy.banchic.domain.entity.BaseEntity;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Unisex extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moreUnisex_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public Unisex(Perfume perfume) {
        this.perfume = perfume;
    }
    @Builder
    public static Unisex createFemale(Perfume perfume) {
        return Unisex.builder()
                .perfume(perfume)
                .build();
    }
}
