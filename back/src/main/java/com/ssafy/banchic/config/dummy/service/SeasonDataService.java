package com.ssafy.banchic.config.dummy.service;

import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.season.*;
import com.ssafy.banchic.repository.PerfumeRepository;
import com.ssafy.banchic.repository.category.season.*;
import com.ssafy.banchic.repository.perfume.GenderRepository;
import com.ssafy.banchic.repository.perfume.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonDataService {

    private final GenderRepository genderRepository;
    private final SeasonRepository seasonRepository;
    private final PerfumeRepository perfumeRepository;
    private final SpringRepository springRepository;
    private final SummerRepository summerRepository;
    private final FallRepository fallRepository;
    private final WinterRepository winterRepository;
    private final DayRepository dayRepository;
    private final NightRepository nightRepository;

    private static class SeasonInfo {
        private float score;
        private String name;

        SeasonInfo(float score, String name) {
            this.score = score;
            this.name = name;
        }
    }
    @Transactional
    public void insertSeasonData() {
        List<Perfume> allPerfume = perfumeRepository.findAll();
        List<SeasonInfo> list = new ArrayList<>();

        for (Perfume perfume : allPerfume) {
            float maxScore = Float.MIN_VALUE;
            String maxSeasonName = null;

            // 각 계절별 최대값 계산
            if (perfume.getSeason().getSpring() > maxScore) {
                maxScore = perfume.getSeason().getSpring();
                maxSeasonName = "Spring";
            }
            if (perfume.getSeason().getSummer() > maxScore) {
                maxScore = perfume.getSeason().getSummer();
                maxSeasonName = "Summer";
            }
            if (perfume.getSeason().getFall() > maxScore) {
                maxScore = perfume.getSeason().getFall();
                maxSeasonName = "Fall";
            }
            if (perfume.getSeason().getWinter() > maxScore) {
                maxScore = perfume.getSeason().getWinter();
                maxSeasonName = "Winter";
            }
            if (perfume.getSeason().getDay() > maxScore) {
                maxScore = perfume.getSeason().getDay();
                maxSeasonName = "Day";
            }
            if (perfume.getSeason().getNight() > maxScore) {
                maxScore = perfume.getSeason().getNight();
                maxSeasonName = "Night";
            }

            // 최대값을 가진 계절에 해당하는 향수를 해당 Repository에 저장
            if (maxSeasonName != null) {
                switch (maxSeasonName) {
                    case "Spring":
                        springRepository.save(new Spring(perfume));
                        break;
                    case "Summer":
                        summerRepository.save(new Summer(perfume));
                        break;
                    case "Fall":
                        fallRepository.save(new Fall(perfume));
                        break;
                    case "Winter":
                        winterRepository.save(new Winter(perfume));
                        break;
                    case "Day":
                        dayRepository.save(new Day(perfume));
                        break;
                    case "Night":
                        nightRepository.save(new Night(perfume));
                        break;
                }
            }
        }
    }
}
