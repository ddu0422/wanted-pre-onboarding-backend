package com.wanted.onboarding.usecase.result;

import lombok.Builder;

import java.util.List;

@Builder
public record FindAllRecruitmentResult(
    List<RecruitmentResult> recruitmentResults
) {}
