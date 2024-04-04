package com.ssafy.banchic.config.dummy.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.perfume.*;
import com.ssafy.banchic.repository.PerfumeRepository;
import com.ssafy.banchic.repository.perfume.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
@RequiredArgsConstructor
public class CsvDataService {

    private final PerfumeRepository perfumeRepository;
    private final GenderRepository genderRepository;
    private final LongevityRepository longevityRepository;
    private final PriceRepository priceRepository;
    private final SeasonRepository seasonRepository;
    private final SillageRepository sillageRepository;
    private final LikeabilityRepository likeabilityRepository;

    @Transactional
    public void importCsvData(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath, Charset.forName("EUC-KR")));) {
            String[] line;
            int idx = 0;
            while ((line = reader.readNext()) != null) {
//                System.out.println(line.length);
                System.out.println(idx);
                String.join(", ", line);
//                System.out.println(line[39]);

                if (idx == 0) {
                    idx++;
                    continue;
                }
                else {
                    line[6] = line[6].replaceAll(",", "");
                    for (int i = 7; i <= 19; i++) {
                        line[i] = line[i].replaceAll("%", "");
                    }


                    // 최대 열 : 40

                    // price entity -> [34 ~ 38]
                    Price price = Price.builder()
                            .wayOverpriced(Integer.parseInt(line[34]))
                            .overpriced(Integer.parseInt(line[35]))
                            .ok(Integer.parseInt(line[36]))
                            .goodValue(Integer.parseInt(line[37]))
                            .greatValue(Integer.parseInt(line[38]))
                            .build();

                    priceRepository.save(price);

                    // gender entity -> [29 ~ 33]
                    Gender gender = Gender.builder()
                            .female(Integer.parseInt(line[29]))
                            .moreFemale(Integer.parseInt(line[30]))
                            .unisex(Integer.parseInt(line[31]))
                            .moreMale(Integer.parseInt(line[32]))
                            .male(Integer.parseInt(line[33]))
                            .build();

                    genderRepository.save(gender);

                    // Longevity entity -> [25 ~ 28]
                    Longevity longevity = Longevity.builder()
                            .intimate(Integer.parseInt(line[25]))
                            .moderate(Integer.parseInt(line[26]))
                            .strong(Integer.parseInt(line[27]))
                            .enormous(Integer.parseInt(line[28]))
                            .build();

                    longevityRepository.save(longevity);

                    // sillage entity -> [20 ~ 24]
                    Sillage sillage = Sillage.builder()
                            .veryWeak(Integer.parseInt(line[20]))
                            .weak(Integer.parseInt(line[21]))
                            .moderate(Integer.parseInt(line[22]))
                            .longLasting(Integer.parseInt(line[23]))
                            .eternal(Integer.parseInt(line[24]))
                            .build();

                    sillageRepository.save(sillage);

                    // season entity -> [14 ~ 19]
                    Season season = Season.builder()
                            .winter(Float.parseFloat(line[14]))
                            .spring(Float.parseFloat(line[15]))
                            .summer(Float.parseFloat(line[16]))
                            .fall(Float.parseFloat(line[17]))
                            .day(Float.parseFloat(line[18]))
                            .night(Float.parseFloat(line[19]))
                            .build();

                    seasonRepository.save(season);

                    // likeability entity -> [9 ~ 13]
                    Likeability likeability = Likeability.builder()
                            .love(Float.parseFloat(line[9]))
                            .heart(Float.parseFloat(line[10]))
                            .ok(Float.parseFloat(line[11]))
                            .dislike(Float.parseFloat(line[12]))
                            .hate(Float.parseFloat(line[13]))
                            .build();

                    likeabilityRepository.save(likeability);

                    // 나머지 요소들 perfume entity -> [0 ~ 8]
                    Perfume perfume = Perfume.builder()
                            .perfumeName(line[0])
                            .perfumeImg(line[1])
                            .brandName(line[2])
                            .brandImg(line[3])
                            .rate(Float.parseFloat(line[4]))
                            .bestRate(Integer.parseInt(line[5]))
                            .vote(Integer.parseInt(line[6]))
                            .accords(line[7])
                            .notes(line[8])
                            .likeability(likeability)
                            .season(season)
                            .sillage(sillage)
                            .longevity(longevity)
                            .gender(gender)
                            .price(price)
                            .koreanName(line[39])
                            .build();

                    perfumeRepository.save(perfume);
                    idx++;
                }
//                System.out.println("line[7] = " + line[7]);
//                System.out.println("line[11] = " + line[11]);
                // 이후 처리 로직 추가
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
