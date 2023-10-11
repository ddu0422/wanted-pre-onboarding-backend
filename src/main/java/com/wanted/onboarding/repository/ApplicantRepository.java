package com.wanted.onboarding.repository;

import com.wanted.onboarding.model.Applicant;
import com.wanted.onboarding.model.Member;
import com.wanted.onboarding.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    boolean existsByMemberAndRecruitment(Member member, Recruitment recruitment);
}
