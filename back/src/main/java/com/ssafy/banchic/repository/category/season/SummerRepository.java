package com.ssafy.banchic.repository.category.season;

import com.ssafy.banchic.domain.entity.perfume.season.Summer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummerRepository extends JpaRepository<Summer, Long> {

    Page<Summer> findAll(Pageable pageable);

}
