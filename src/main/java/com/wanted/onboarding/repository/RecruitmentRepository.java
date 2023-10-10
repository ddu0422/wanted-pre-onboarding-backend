package com.wanted.onboarding.repository;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, JpaSpecificationExecutor<Recruitment> {
    List<Recruitment> findByCompany(Company company);
}
