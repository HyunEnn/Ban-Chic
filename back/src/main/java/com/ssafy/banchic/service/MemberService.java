package com.ssafy.banchic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.banchic.domain.dto.request.PersuitReq;
import com.ssafy.banchic.domain.dto.request.UpdateNicknameReq;
import com.ssafy.banchic.domain.dto.response.MemberInfoRes;
import com.ssafy.banchic.domain.dto.response.MemberNicknameRes;
import com.ssafy.banchic.domain.dto.response.MemberReviewRes;
import com.ssafy.banchic.domain.dto.response.PerfumeOverviewRes;
import com.ssafy.banchic.domain.dto.response.RecommendByImageRes;
import com.ssafy.banchic.domain.entity.Heart;
import com.ssafy.banchic.domain.entity.Member;
import com.ssafy.banchic.domain.entity.Perfume;
import com.ssafy.banchic.domain.entity.Persuit;
import com.ssafy.banchic.domain.entity.Recommend;
import com.ssafy.banchic.domain.entity.Review;
import com.ssafy.banchic.exception.CustomException;
import com.ssafy.banchic.exception.ErrorCode;
import com.ssafy.banchic.repository.HeartRepository;
import com.ssafy.banchic.repository.MemberRepository;
import com.ssafy.banchic.repository.PerfumeRepository;
import com.ssafy.banchic.repository.PerfumeReviewRepository;
import com.ssafy.banchic.repository.PersuitRepository;
import com.ssafy.banchic.repository.RecommendRepository;
import com.ssafy.banchic.util.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final String FAST_API_URL = "http://j10b109.p.ssafy.io:9876";

    private final FileUploadService fileUploadService;

    private final MemberRepository memberRepository;
    private final PerfumeRepository perfumeRepository;
    private final PersuitRepository persuitRepository;
    private final RecommendRepository recommendRepository;
    private final PerfumeReviewRepository perfumeReviewRepository;
    private final HeartRepository heartRepository;

    private final RestTemplate restTemplate;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<MemberReviewRes> getMemberReview(Long memberId, HttpServletRequest httpServletRequest) {
        Member memberFromAccessToken = getMemberFromAccessToken(httpServletRequest);

        if (!memberFromAccessToken.getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NOT_FOUND_ID);
        }

        List<Review> findMemberReviews = perfumeReviewRepository.findByMemberId(memberId);

        return findMemberReviews
                .stream()
                .map(MemberReviewRes::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PerfumeOverviewRes> getMemberHeart(Long memberId, HttpServletRequest httpServletRequest) {
        Member memberFromAccessToken = getMemberFromAccessToken(httpServletRequest);

        if(!memberFromAccessToken.getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NOT_FOUND_ID);
        }

        List<Heart> memberHearts = heartRepository.findByMemberIdOrderByCreatedAtDesc(memberId);

        return memberHearts.stream()
                .map(e -> PerfumeOverviewRes.from(e.getPerfume()))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberInfoRes getMemberInfo(Long memberId, HttpServletRequest httpServletRequest) {
        Member memberFromAccessToken = getMemberFromAccessToken(httpServletRequest);

        if (!memberFromAccessToken.getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

        return MemberInfoRes.from(memberFromAccessToken);
    }

    public void delete(Long memberId, HttpServletRequest httpServletRequest) {
        Member memberFromAccessToken = getMemberFromAccessToken(httpServletRequest);

        if (!memberFromAccessToken.getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

        memberRepository.deleteById(memberId);
    }

    public MemberNicknameRes updateNickname(Long memberId, UpdateNicknameReq updateNicknameReq, HttpServletRequest httpServletRequest) {
        Member memberFromAccessToken = getMemberFromAccessToken(httpServletRequest);

        if (!memberFromAccessToken.getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NOT_FOUND_ID);
        }

        memberFromAccessToken.updateNickname(updateNicknameReq.getNickname());
        Member updateMember = memberRepository.save(memberFromAccessToken);

        return MemberNicknameRes.from(updateMember);
    }

    public String updateImage(Long memberId, MultipartFile file, HttpServletRequest httpServletRequest) {
        Member memberFromAccessToken = getMemberFromAccessToken(httpServletRequest);

        if (!memberFromAccessToken.getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

        if (!(memberFromAccessToken.getImage() == null || memberFromAccessToken.getImage().isEmpty())) {
            fileUploadService.delete(memberFromAccessToken.getImage());
        }

        String imgUrl = fileUploadService.save("member/", file);
        memberFromAccessToken.updateImage(imgUrl);

        memberRepository.save(memberFromAccessToken);

        return imgUrl;
    }

    public List<PerfumeOverviewRes> survey(PersuitReq req, HttpServletRequest httpServletRequest) {
        Member member = getMemberFromAccessToken(httpServletRequest);

        String url = FAST_API_URL + "/api/recommend";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        StringBuilder sb = new StringBuilder();
        String requestBody = sb.append("{")
            .append("\"clear\": " ).append(req.getClear() ? 1 : 0).append(", ")
            .append("\"romantic\": " ).append(req.getRomantic() ? 1 : 0).append(", ")
            .append("\"pretty\": " ).append(req.getPretty() ? 1 : 0).append(", ")
            .append("\"coolcasual\": " ).append(req.getCoolcasual() ? 1 : 0).append(", ")
            .append("\"casual\": " ).append(req.getCasual() ? 1 : 0).append(", ")
            .append("\"natural\": " ).append(req.getNatural() ? 1 : 0).append(", ")
            .append("\"elegant\": " ).append(req.getElegant() ? 1 : 0).append(", ")
            .append("\"dynamic\": " ).append(req.getDynamic() ? 1 : 0).append(", ")
            .append("\"wild\": " ).append(req.getWild() ? 1 : 0).append(", ")
            .append("\"gorgeous\": " ).append(req.getGorgeous() ? 1 : 0).append(", ")
            .append("\"chic\": " ).append(req.getChic() ? 1 : 0).append(", ")
            .append("\"modern\": " ).append(req.getModern() ? 1 : 0).append(", ")
            .append("\"classic\": " ).append(req.getClassic() ? 1 : 0).append(", ")
            .append("\"dandy\": " ).append(req.getDandy() ? 1 : 0)
            .append("}").toString();

        Persuit persuit = Persuit.from(req, member);
        if (persuitRepository.existsByMember(member)) {
            persuitRepository.deleteByMember(member);
            persuitRepository.flush();
        }
        persuitRepository.save(persuit);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        Recommendation recommResponse = null;

        try {
            recommResponse = restTemplate.postForObject(url, requestEntity, Recommendation.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorCode.FAIL_IN_FASTAPI);
        }

        Recommend recommend = new Recommend();
        recommend.setMember(member);
        recommend.setOne(recommResponse.getRecommendIndex().get(0));
        recommend.setTwo(recommResponse.getRecommendIndex().get(1));
        recommend.setThree(recommResponse.getRecommendIndex().get(2));
        recommend.setFour(recommResponse.getRecommendIndex().get(3));
        recommend.setFive(recommResponse.getRecommendIndex().get(4));
        recommend.setSix(recommResponse.getRecommendIndex().get(5));
        recommend.setSeven(recommResponse.getRecommendIndex().get(6));
        recommend.setEight(recommResponse.getRecommendIndex().get(7));
        recommend.setNine(recommResponse.getRecommendIndex().get(8));
        recommend.setTen(recommResponse.getRecommendIndex().get(9));

        if (recommendRepository.existsByMember(member)) {
            recommendRepository.deleteByMember(member);
            recommendRepository.flush();
        }
        recommendRepository.save(recommend);

        List<PerfumeOverviewRes> recommList = new ArrayList<>();
        recommList.add(findById(recommend.getOne()));
        recommList.add(findById(recommend.getTwo()));
        recommList.add(findById(recommend.getThree()));
        recommList.add(findById(recommend.getFour()));
        recommList.add(findById(recommend.getFive()));
        recommList.add(findById(recommend.getSix()));
        recommList.add(findById(recommend.getSeven()));
        recommList.add(findById(recommend.getEight()));
        recommList.add(findById(recommend.getNine()));
        recommList.add(findById(recommend.getTen()));

        return recommList;
    }

    @Getter
    public static class Recommendation {
        private List<Integer> recommend_index;

        public List<Integer> getRecommendIndex() {
            return recommend_index;
        }
    }

    public List<PerfumeOverviewRes> getRecommList(HttpServletRequest request) {
        Member member = getMemberFromAccessToken(request);

        Optional<Recommend> optionalRecommend = recommendRepository.findByMember(member);
        if (optionalRecommend.isEmpty()) return null;

        Recommend recommend = optionalRecommend.get();

        List<PerfumeOverviewRes> recommList = new ArrayList<>();
        recommList.add(findById(recommend.getOne()));
        recommList.add(findById(recommend.getTwo()));
        recommList.add(findById(recommend.getThree()));
        recommList.add(findById(recommend.getFour()));
        recommList.add(findById(recommend.getFive()));
        recommList.add(findById(recommend.getSix()));
        recommList.add(findById(recommend.getSeven()));
        recommList.add(findById(recommend.getEight()));
        recommList.add(findById(recommend.getNine()));
        recommList.add(findById(recommend.getTen()));

        return recommList;
    }

    private PerfumeOverviewRes findById(int id) {
        Optional<Perfume> perfume = perfumeRepository.findById(id);
        return perfume.map(PerfumeOverviewRes::from).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Integer> getPersuit(HttpServletRequest httpServletRequest) {
        Member member = getMemberFromAccessToken(httpServletRequest);
        if (!persuitRepository.existsByMember(member)) {
            return null;
        }

        Persuit persuit = persuitRepository.findByMember(member).get();
        List<Integer> list = new ArrayList<>();
        if (persuit.isClear()) list.add(0);
        if (persuit.isRomantic()) list.add(1);
        if (persuit.isPretty()) list.add(2);
        if (persuit.isCoolcasual()) list.add(3);
        if (persuit.isCasual()) list.add(4);
        if (persuit.isNatural()) list.add(5);
        if (persuit.isElegant()) list.add(6);
        if (persuit.isDynamic()) list.add(7);
        if (persuit.isWild()) list.add(8);
        if (persuit.isGorgeous()) list.add(9);
        if (persuit.isChic()) list.add(10);
        if (persuit.isModern()) list.add(11);
        if (persuit.isClassic()) list.add(12);
        if (persuit.isDandy()) list.add(13);

        return list;
    }

    public RecommendByImageRes recommendByImage(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_FILE);
        }

        String url = FAST_API_URL + "/api/image";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body.add("file", new HttpEntity<>(multipartFile.getBytes(), getHeaders(multipartFile)));
        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_INPUT_FAIL);
        }

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (HttpServerErrorException e) {
            throw new CustomException(ErrorCode.FAIL_IN_FASTAPI);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new CustomException(ErrorCode.JSON_PARSING_FAIL);
        }

        String fashion = rootNode.get("fashion").asText();
        JsonNode recommend_index = rootNode.get("recommend_index");

        List<Integer> idList = new ArrayList<>();
        if (recommend_index.isArray()) {
            Iterator<JsonNode> elements = recommend_index.elements();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                int perfumeId = Integer.parseInt(node.asText());
                idList.add(perfumeId);
            }
        }

        List<PerfumeOverviewRes> perfumeOverviewResList = idList.stream()
            .map(e -> PerfumeOverviewRes.from(perfumeRepository.findById(e)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID))))
            .toList();

        return RecommendByImageRes.builder()
            .fashion(fashion)
            .perfumeOverviewResList(perfumeOverviewResList)
            .build();
    }

    private HttpHeaders getHeaders(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentDispositionFormData("file", file.getOriginalFilename());
        return headers;
    }

    public List<PerfumeOverviewRes> recommendByCf(HttpServletRequest httpServletRequest) {
        Member member = getMemberFromAccessToken(httpServletRequest);

        String url = FAST_API_URL + "/api/user";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        StringBuilder sb = new StringBuilder();
        String requestBody = sb.append("{")
            .append("\"member_id\": " ).append(member.getId())
            .append("}").toString();

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        Recommendation response = null;

        try {
            response = restTemplate.postForObject(url, requestEntity, Recommendation.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorCode.FAIL_IN_FASTAPI);
        }

        List<Integer> recommendIndexList = response.getRecommendIndex();

        List<PerfumeOverviewRes> recommList = new ArrayList<>();
        for (Integer idx : recommendIndexList) {
            recommList.add(findById(idx));
        }

        return recommList;
    }

    @Transactional(readOnly = true)
    public Member getMemberFromAccessToken(HttpServletRequest request) {
        Member memberFromAccessToken = tokenProvider.getMemberFromAccessToken(request);
        return memberRepository.findById(memberFromAccessToken.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID));
    }

}
