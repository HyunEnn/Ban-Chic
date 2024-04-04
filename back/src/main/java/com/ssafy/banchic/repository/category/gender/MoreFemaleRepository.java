package com.ssafy.banchic.repository.category.gender;

import com.ssafy.banchic.domain.entity.perfume.gender.MoreFemale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoreFemaleRepository extends JpaRepository<MoreFemale, Long> {

    Page<MoreFemale> findAll(Pageable pageable);

}
