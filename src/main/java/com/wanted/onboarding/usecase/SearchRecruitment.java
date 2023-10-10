package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.query.SearchRecruitmentQuery;
import com.wanted.onboarding.usecase.result.RecruitmentResult;
import com.wanted.onboarding.usecase.result.SearchRecruitmentResult;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchRecruitment {

    private final RecruitmentRepository recruitmentRepository;

    public SearchRecruitmentResult execute(SearchRecruitmentQuery query) {
        String keyword = query.keyword();

        List<RecruitmentResult> results = recruitmentRepository.findAll(
            containCompanyName(keyword).or(containPosition(keyword).or(containSkill(keyword)))
        ).stream()
            .map(this::changeRecruitmentResult)
            .toList();

        return SearchRecruitmentResult.builder()
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

    private Specification<Recruitment> containCompanyName(String keyword) {
        return Specification.where(((root, query1, criteriaBuilder) -> {
            Join<Recruitment, Company> join = root.join("company");
            return criteriaBuilder.like(join.get("name"), "%" + keyword + "%");
        }));
    }

    private Specification<Recruitment> containPosition(String keyword) {
        return Specification.where(
            ((root, query1, criteriaBuilder) -> criteriaBuilder.like(root.get("position"), "%" + keyword + "%"))
        );
    }

    private Specification<Recruitment> containSkill(String keyword) {
        return Specification.where(
            ((root, query1, criteriaBuilder) -> criteriaBuilder.like(root.get("skill"), "%" + keyword + "%"))
        );
    }
}
