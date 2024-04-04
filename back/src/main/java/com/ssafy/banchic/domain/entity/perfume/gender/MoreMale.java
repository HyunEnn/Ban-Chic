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
public class MoreMale extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moreMale_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public MoreMale(Perfume perfume) {
        this.perfume = perfume;
    }
    @Builder
    public static MoreMale createFemale(Perfume perfume) {
        return MoreMale.builder()
                .perfume(perfume)
                .build();
    }
}
