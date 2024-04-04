package com.ssafy.banchic.config.dummy.service;

import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.gender.*;
import com.ssafy.banchic.repository.PerfumeRepository;
import com.ssafy.banchic.repository.category.gender.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderDataService {

    private final PerfumeRepository perfumeRepository;
    private final FemaleRepository femaleRepository;
    private final MaleRepository maleRepository;
    private final MoreFemaleRepository moreFemaleRepository;
    private final MoreMaleRepository moreMaleRepository;
    private final UnisexRepository unisexRepository;

    @Transactional
    public void insertGenderData() {
        List<Perfume> ListPerfumes = perfumeRepository.findAll();
        /**
         * perfume data 조회 후에 , season 조회와 유사하게 작성하고,
         * 데이터 매핑에 대한 성능 이슈는 Paging 처리로 해결
         */
        for(Perfume perfume : ListPerfumes) {
            int maxScore = Integer.MIN_VALUE;
            String maxGenderName = null;

            if(perfume.getGender().getMale() > maxScore) {
                maxScore = perfume.getGender().getMale();
                maxGenderName = "Male";
            }
            if(perfume.getGender().getFemale() > maxScore) {
                maxScore = perfume.getGender().getFemale();
                maxGenderName = "Female";
            }
            if(perfume.getGender().getMoreFemale() > maxScore) {
                maxScore = perfume.getGender().getMoreFemale();
                maxGenderName = "MoreFemale";
            }
            if(perfume.getGender().getMoreMale() > maxScore) {
                maxScore = perfume.getGender().getMoreMale();
                maxGenderName = "MoreMale";
            }
            if(perfume.getGender().getUnisex() > maxScore) {
                maxScore = perfume.getGender().getUnisex();
                maxGenderName = "Unisex";
            }


            if(maxGenderName != null) {
                switch (maxGenderName) {
                    case "Male":
                        maleRepository.save(new Male(perfume));
                        break;
                    case "Female":
                        femaleRepository.save(new Female(perfume));
                        break;
                    case "MoreFemale":
                        moreFemaleRepository.save(new MoreFemale(perfume));
                        break;
                    case "MoreMale":
                        moreMaleRepository.save(new MoreMale(perfume));
                        break;
                    case "Unisex":
                        unisexRepository.save(new Unisex(perfume));
                        break;
                }
            }


        }
    }

}
