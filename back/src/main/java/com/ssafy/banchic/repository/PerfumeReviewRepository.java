package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfumeReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> getReviewsByPerfume(Perfume perfume, Pageable pageable);
    List<Review> findByMemberId(Long memberId);

}
