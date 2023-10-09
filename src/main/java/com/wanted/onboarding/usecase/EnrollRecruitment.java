package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.CompanyRepository;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.command.EnrollRecruitmentCommand;
import com.wanted.onboarding.usecase.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollRecruitment {

    private final CompanyRepository companyRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void execute(EnrollRecruitmentCommand command) {
        Company company = companyRepository.findById(command.companyId())
            .orElseThrow(CompanyNotFoundException::new);
        Recruitment recruitment = Recruitment.of(command.position(), command.reward(), command.description(), command.skill(), company);

        recruitmentRepository.save(recruitment);
    }
}
