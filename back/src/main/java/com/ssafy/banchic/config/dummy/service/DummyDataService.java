package com.ssafy.banchic.config.dummy.service;

import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.Review;
import com.ssafy.banchic.domain.entity.perfume.*;
import com.ssafy.banchic.domain.type.OAuthProvider;
import com.ssafy.banchic.repository.MemberRepository;
import com.ssafy.banchic.repository.PerfumeRepository;
import com.ssafy.banchic.repository.PerfumeReviewRepository;
import com.ssafy.banchic.repository.perfume.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DummyDataService {

    private final EntityManager em;
    private final SillageRepository sillageRepository;
    private final SeasonRepository seasonRepository;
    private final PriceRepository priceRepository;
    private final LongevityRepository longevityRepository;
    private final GenderRepository genderRepository;
    private final PerfumeRepository perfumeRepository;
    private final MemberRepository memberRepository;
    private final PerfumeReviewRepository perfumeReviewRepository;
    @Transactional
    public void insertDummyData() {
        Member member = Member.builder()
                .email("ssafy01@ssafy.com")
                .image("test1")
                .nickname("싸피1")
                .oAuthProvider(OAuthProvider.KAKAO)
                .build();

        memberRepository.save(member);

        Member member2 = Member.builder()
                .email("ssafy02@ssafy.com")
                .image("test2")
                .nickname("싸피 관리자")
                .oAuthProvider(OAuthProvider.NAVER)
                .build();
        memberRepository.save(member2);

        Member realMember = Member.builder()
                .email("danny100e@gmail.com")
                .image("test3.jpg")
                .nickname("현준")
                .oAuthProvider(OAuthProvider.KAKAO)
                .build();
        memberRepository.save(realMember);

        Sillage sillage1 = Sillage.builder().veryWeak(1)
                .weak(2)
                .moderate(3)
                .longLasting(4)
                .eternal(5)
                .build();
        sillageRepository.save(sillage1);

        Season season = Season.builder()
                .spring(43.5F)
                .summer(123.4F)
                .fall(44.5F)
                .winter(15.2F)
                .day(55.5F)
                .night(65.1F)
                .build();
        seasonRepository.save(season);

        Price price = Price.builder()
                .wayOverpriced(50)
                .overpriced(100)
                .ok(1000)
                .goodValue(800)
                .greatValue(500)
                .build();
        priceRepository.save(price);

        Longevity longevity = Longevity.builder()
                .intimate(100)
                .moderate(70)
                .strong(20)
                .enormous(5)
                .build();
        longevityRepository.save(longevity);

        Gender gender = Gender.builder()
                .female(100)
                .male(50)
                .unisex(1000)
                .moreMale(20)
                .moreFemale(40)
                .build();
        genderRepository.save(gender);

        // perfume에 대한 값 주입
        Perfume perfume = Perfume.builder()
                .perfumeName("테스트 향수1")
                .perfumeImg("test.png")
                .brandName("조말론")
                .brandImg("brand.png")
                .accords("1234")
                .notes("향료1, 향료2")
                .year(2023)
                .bestRate(5)
                .rate(4.7F)
                .sillage(sillage1)
                .longevity(longevity)
                .price(price)
                .gender(gender)
                .season(season)
                .build();
        perfumeRepository.save(perfume);

        Perfume perfume2 = Perfume.builder()
                .perfumeName("테스트 향수2")
                .perfumeImg("test.png")
                .brandName("샤넬")
                .brandImg("brand.png")
                .accords("1234")
                .notes("향료3, 향료4")
                .year(2023)
                .bestRate(4)
                .rate(4.6F)
//                .sillage(sillage1)
//                .longevity(longevity)
//                .price(price)
//                .gender(gender)
//                .season(season)
                .build();

        perfumeRepository.save(perfume2);

        Review review = Review.builder()
                .content("엄둔딕")
                .imgUrl("test.jpg")
                .rate(5)
                .member(realMember)
                .perfume(perfume)
                .build();

        perfumeReviewRepository.save(review);

    }
}
