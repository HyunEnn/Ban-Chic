package com.ssafy.banchic.domain.entity.perfume;

import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.season.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.NavigableMap;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private int id;

    private float spring;
    private float summer;
    private float fall;
    private float winter;
    private float day;
    private float night;

}
