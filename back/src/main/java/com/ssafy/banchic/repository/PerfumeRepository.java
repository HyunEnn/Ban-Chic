package com.ssafy.banchic.repository;

import com.ssafy.banchic.domain.entity.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {

//    Page<Perfume> findByBrandName(String brandName);
}
