package com.ssafy.banchic.repository.category.season;

import com.ssafy.banchic.domain.entity.perfume.season.Fall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FallRepository extends JpaRepository<Fall, Long> {
    Page<Fall> findAll(Pageable pageable);
}
