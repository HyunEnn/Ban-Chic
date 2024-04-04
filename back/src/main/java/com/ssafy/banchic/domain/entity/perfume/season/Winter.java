package com.ssafy.banchic.domain.entity.perfume.season;


import com.ssafy.banchic.domain.entity.BaseEntity;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.Season;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Winter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "winter_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public Winter(Perfume perfume) {
        this.perfume = perfume;
    }
    @Builder
    public static Winter from(Perfume perfume) {
        return Winter.builder()
                .perfume(perfume)
                .build();
    }
}
