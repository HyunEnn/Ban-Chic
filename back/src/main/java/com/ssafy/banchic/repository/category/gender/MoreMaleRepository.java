package com.ssafy.banchic.repository.category.gender;

import com.ssafy.banchic.domain.entity.perfume.gender.MoreFemale;
import com.ssafy.banchic.domain.entity.perfume.gender.MoreMale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoreMaleRepository extends JpaRepository<MoreMale, Long> {
    Page<MoreMale> findAll(Pageable pageable);
}
