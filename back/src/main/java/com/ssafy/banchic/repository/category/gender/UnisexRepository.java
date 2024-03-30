package com.ssafy.banchic.repository.category.gender;

import com.ssafy.banchic.domain.entity.perfume.gender.MoreFemale;
import com.ssafy.banchic.domain.entity.perfume.gender.Unisex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnisexRepository extends JpaRepository<Unisex, Long> {

    Page<Unisex> findAll(Pageable pageable);
}
