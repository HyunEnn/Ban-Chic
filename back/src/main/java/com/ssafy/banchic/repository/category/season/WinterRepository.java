package com.ssafy.banchic.repository.category.season;

import com.ssafy.banchic.domain.entity.perfume.season.Winter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinterRepository extends JpaRepository<Winter, Long> {

    Page<Winter> findAll(Pageable pageable);

}
