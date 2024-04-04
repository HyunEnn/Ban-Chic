package com.ssafy.banchic.service;

import com.ssafy.banchic.domain.dto.request.ReviewReq;
import com.ssafy.banchic.domain.dto.response.ReviewRes;
import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.Review;
import com.ssafy.banchic.exception.CustomException;
import com.ssafy.banchic.exception.ErrorCode;
import com.ssafy.banchic.repository.MemberRepository;
import com.ssafy.banchic.repository.PerfumeRepository;
import com.ssafy.banchic.repository.PerfumeReviewRepository;
import com.ssafy.banchic.util.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfumeReviewService {

    private final PerfumeRepository perfumeRepository;
    private final PerfumeReviewRepository perfumeReviewRepository;
    private final MemberRepository memberRepository;
    private final FileUploadService fileUploadService;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public Page<ReviewRes> getList(Integer perfumeId, Pageable pageable) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID));
        Page<Review> reviews = perfumeReviewRepository.getReviewsByPerfume(perfume, pageable);
        return reviews.map(ReviewRes::from);
    }

    public ReviewRes create(Integer perfumeId, ReviewReq reviewReq, MultipartFile file,HttpServletRequest httpServletRequest) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID));

        Member member = getMemberFromAccessToken(httpServletRequest);

        String imgUrl = null;
        if (file != null && !file.isEmpty()) {
            imgUrl = fileUploadService.save("review/", file);
        }

        Review review = Review.builder()
            .rate(reviewReq.getRate())
            .content(reviewReq.getContent())
            .imgUrl(imgUrl)
            .perfume(perfume)
            .member(member)
            .build();

        return ReviewRes.from(perfumeReviewRepository.save(review));
    }

    public ReviewRes update(
        Integer perfumeId, Long reviewId, ReviewReq reviewReq, HttpServletRequest httpServletRequest) {
        Review review = perfumeReviewRepository.findById(reviewId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID));
        Member member = getMemberFromAccessToken(httpServletRequest);

        if (!review.getPerfume().getId().equals(perfumeId)) {
            throw new CustomException(ErrorCode.INVALID_RESOURCE);
        }

        if (!review.getMember().getId().equals(member.getId())) {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

        review.update(reviewReq);
        perfumeReviewRepository.save(review);

        return ReviewRes.from(review);
    }

    public void delete(
        Integer perfumeId, Long reviewId, HttpServletRequest httpServletRequest) {
        Review review = perfumeReviewRepository.findById(reviewId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID));
        Member member = getMemberFromAccessToken(httpServletRequest);

        if (!review.getPerfume().getId().equals(perfumeId)) {
            throw new CustomException(ErrorCode.INVALID_RESOURCE);
        }

        if (!review.getMember().getId().equals(member.getId())) {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

        perfumeReviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public Member getMemberFromAccessToken(HttpServletRequest request) {
        Member memberFromAccessToken = tokenProvider.getMemberFromAccessToken(request);
        return memberRepository.findById(memberFromAccessToken.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID));
    }

}
