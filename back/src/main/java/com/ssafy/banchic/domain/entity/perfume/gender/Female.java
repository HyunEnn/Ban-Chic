package com.ssafy.banchic.domain.entity.perfume.gender;

import com.ssafy.banchic.domain.entity.BaseEntity;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.Gender;
import com.ssafy.banchic.domain.entity.perfume.Season;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Female extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "female_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public Female(Perfume perfume) {
        this.perfume = perfume;
    }

    @Builder
    public static Female createFemale(Perfume perfume) {
        return Female.builder()
                .perfume(perfume)
                .build();
    }
}
