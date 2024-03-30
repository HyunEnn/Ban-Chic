package com.ssafy.banchic.repository.perfume;

import com.ssafy.banchic.domain.entity.perfume.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {

}
