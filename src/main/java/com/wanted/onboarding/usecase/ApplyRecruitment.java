package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Applicant;
import com.wanted.onboarding.model.Member;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.ApplicantRepository;
import com.wanted.onboarding.repository.MemberRepository;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.exception.AlreadyAppliedForRecruitmentException;
import com.wanted.onboarding.usecase.exception.MemberNotFoundException;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyRecruitment {

    private final MemberRepository memberRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final ApplicantRepository applicantRepository;

    @Transactional
    public void execute() {
        Member member = memberRepository.findById(1L)
            .orElseThrow(MemberNotFoundException::new);

        Recruitment recruitment = recruitmentRepository.findById(1L)
            .orElseThrow(RecruitmentNotFoundException::new);

        checkAppliedRecruitment(member, recruitment);

        applicantRepository.save(Applicant.of(member, recruitment));
    }

    private void checkAppliedRecruitment(Member member, Recruitment recruitment) {
        boolean isApplied = applicantRepository.existsByMemberAndRecruitment(member, recruitment);

        if (isApplied) {
            throw new AlreadyAppliedForRecruitmentException();
        }
    }
}
