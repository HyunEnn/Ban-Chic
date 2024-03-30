package com.ssafy.banchic.domain.entity.perfume;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Likeability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeability_id")
    private Long id;

    private float love;
    private float heart;
    private float ok;
    private float dislike;
    private float hate;

}
