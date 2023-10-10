package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.result.FindAllRecruitmentResult;
import com.wanted.onboarding.usecase.result.RecruitmentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllRecruitment {

    private final RecruitmentRepository recruitmentRepository;

    public FindAllRecruitmentResult execute() {
        List<RecruitmentResult> results = recruitmentRepository.findAll().stream()
            .map(this::changeRecruitmentResult)
            .toList();

        return FindAllRecruitmentResult.builder()
            .recruitmentResults(results)
            .build();
    }

    private RecruitmentResult changeRecruitmentResult(Recruitment recruitment) {
        return RecruitmentResult.builder()
            .recruitmentId(recruitment.getId())
            .companyName(recruitment.getCompany().getName())
            .country(recruitment.getCompany().getCountry())
            .region(recruitment.getCompany().getRegion())
            .position(recruitment.getPosition())
            .reward(recruitment.getReward().intValue())
            .skill(recruitment.getSkill())
            .build();
    }
}
