package com.ssafy.banchic.repository.category.gender;

import com.ssafy.banchic.domain.entity.perfume.gender.Female;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FemaleRepository extends JpaRepository<Female, Long> {
    Page<Female> findAll(Pageable pageable);

}
