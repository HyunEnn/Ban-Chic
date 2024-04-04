package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMember(Member member);
    Optional<RefreshToken> findByKeyValue(String value);

}