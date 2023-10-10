package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.CompanyRepository;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.command.RemoveRecruitmentCommand;
import com.wanted.onboarding.usecase.exception.CompanyNotFoundException;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoveRecruitment {

    private final CompanyRepository companyRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void execute(RemoveRecruitmentCommand command) {
        Company company = companyRepository.findById(command.companyId())
            .orElseThrow(CompanyNotFoundException::new);
        Recruitment recruitment = recruitmentRepository.findById(command.recruitmentId())
            .orElseThrow(RecruitmentNotFoundException::new);

        recruitment.isSame(company);

        recruitmentRepository.deleteById(command.recruitmentId());
    }
}
