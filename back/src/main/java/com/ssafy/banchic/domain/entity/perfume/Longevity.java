package com.ssafy.banchic.domain.entity.perfume;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Longevity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "longeivty_id")
    private Long id;

    private int intimate;
    private int moderate;
    private int strong;
    private int enormous;

}
