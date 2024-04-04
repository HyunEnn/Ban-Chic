package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.type.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean findByNickname(String nickname);

    @Query("select m from Member m where m.email = :email and m.oAuthProvider = :oauthProvider")
    Member findByEmailAndOAuthProvider(@Param("email") String email, @Param("oauthProvider") OAuthProvider oAuthProvider);

}
