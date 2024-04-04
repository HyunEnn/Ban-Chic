package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Heart;
import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsByMemberAndPerfume(Member member, Perfume perfume);
    void deleteByMemberAndPerfume(Member member, Perfume perfume);
    List<Heart> findByMemberIdOrderByCreatedAtDesc(Long memberId);

}
