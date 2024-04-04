package com.ssafy.banchic.config.dummy;

import com.ssafy.banchic.config.dummy.service.CsvDataService;
import com.ssafy.banchic.config.dummy.service.DummyDataService;
import com.ssafy.banchic.config.dummy.service.GenderDataService;
import com.ssafy.banchic.config.dummy.service.SeasonDataService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class DataInitalizationRunner implements ApplicationRunner {

    private final DummyDataService dummyDataService;
    private final CsvDataService csvDataService;
    private final SeasonDataService seasonDataService;
    private final GenderDataService genderDataService;

    public DataInitalizationRunner(DummyDataService dummyDataService, CsvDataService csvDataService, SeasonDataService seasonDataService,
                                   GenderDataService genderDataService) {
        this.dummyDataService = dummyDataService;
        this.csvDataService = csvDataService;
        this.seasonDataService = seasonDataService;
        this.genderDataService = genderDataService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 애플리케이션 초기화 시점에서 DummyData 실행
//        dummyDataService.insertDummyData();
        csvDataService.importCsvData("C:\\Users\\SSAFY\\Downloads\\RealFinalPerfume.csv");
        seasonDataService.insertSeasonData();
        genderDataService.insertGenderData();
    }
}
