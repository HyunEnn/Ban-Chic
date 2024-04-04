package com.ssafy.banchic.domain.entity;

import com.ssafy.banchic.domain.dto.request.PersuitReq;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persuit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persuit_id")
    private Long id;

    private boolean clear;
    private boolean romantic;
    private boolean pretty;
    private boolean coolcasual;
    private boolean casual;
    @Column(name = "natural_persuit")
    private boolean natural;
    private boolean elegant;
    private boolean dynamic;
    private boolean wild;
    private boolean gorgeous;
    private boolean chic;
    private boolean modern;
    private boolean classic;
    private boolean dandy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public static Persuit from(PersuitReq req, Member member) {
        return Persuit.builder()
            .clear(req.getClear())
            .romantic(req.getRomantic())
            .pretty(req.getPretty())
            .coolcasual(req.getCoolcasual())
            .casual(req.getCasual())
            .natural(req.getNatural())
            .elegant(req.getElegant())
            .dynamic(req.getDynamic())
            .wild(req.getWild())
            .gorgeous(req.getGorgeous())
            .chic(req.getChic())
            .modern(req.getModern())
            .classic(req.getClassic())
            .dandy(req.getDandy())
            .member(member)
            .build();
    }

}
