package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import com.wanted.onboarding.usecase.query.GetRecruitmentQuery;
import com.wanted.onboarding.usecase.result.GetRecruitmentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRecruitment {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public GetRecruitmentResult execute(GetRecruitmentQuery query) {
        Recruitment recruitment = recruitmentRepository.findById(query.recruitmentId())
            .orElseThrow(RecruitmentNotFoundException::new);
        Company company = recruitment.getCompany();

        List<Long> otherRecruitmentIds = recruitmentRepository.findByCompany(company).stream()
                .filter(otherRecruitment -> otherRecruitment.isNotSame(recruitment))
                .map(Recruitment::getId)
                .toList();

        return GetRecruitmentResult.builder()
            .recruitmentId(recruitment.getId())
            .companyName(company.getName())
            .country(company.getCountry())
            .region(company.getRegion())
            .position(recruitment.getPosition())
            .reward(recruitment.getReward().intValue())
            .skill(recruitment.getSkill())
            .description(recruitment.getDescription())
            .otherRecruitmentIds(otherRecruitmentIds)
            .build();
    }
}
