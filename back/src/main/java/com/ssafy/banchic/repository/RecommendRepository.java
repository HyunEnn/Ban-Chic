package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.Recommend;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    Optional<Recommend> findByMember(Member member);
    boolean existsByMember(Member member);
    void deleteByMember(Member member);

}
