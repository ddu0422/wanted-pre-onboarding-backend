package com.wanted.onboarding.repository;

import com.wanted.onboarding.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
