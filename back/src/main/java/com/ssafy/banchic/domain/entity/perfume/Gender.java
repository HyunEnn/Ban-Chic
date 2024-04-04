package com.ssafy.banchic.domain.entity.perfume;

import com.ssafy.banchic.domain.entity.Perfume;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long id;

    private int female;
    private int moreFemale;
    private int unisex;
    private int moreMale;
    private int male;

}
