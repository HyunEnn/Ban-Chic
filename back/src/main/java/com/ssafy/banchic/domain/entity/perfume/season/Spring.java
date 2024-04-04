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
public class Spring extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spring_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    public Spring(Perfume perfume) {
        this.perfume = perfume;
    }
    @Builder
    public static Spring from(Perfume perfume) {
        return Spring.builder()
                .perfume(perfume)
                .build();
    }
}
