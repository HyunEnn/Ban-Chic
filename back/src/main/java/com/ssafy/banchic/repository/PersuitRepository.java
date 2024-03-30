package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.Persuit;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersuitRepository extends JpaRepository<Persuit, Long> {

    boolean existsByMember(Member member);
    void deleteByMember(Member member);
    Optional<Persuit> findByMember(Member member);

}
