package com.ssafy.banchic.repository.category.season;

import com.ssafy.banchic.domain.entity.perfume.season.Night;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NightRepository extends JpaRepository<Night, Long> {

    Page<Night> findAll(Pageable pageable);
}
