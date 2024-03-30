package com.ssafy.banchic.repository.category.gender;

import com.ssafy.banchic.domain.entity.perfume.gender.Male;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaleRepository extends JpaRepository<Male, Long> {

    Page<Male> findAll(Pageable pageable);
}
