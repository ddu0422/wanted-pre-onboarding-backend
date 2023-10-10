package com.wanted.onboarding.usecase.result;

import lombok.Builder;

@Builder
public record RecruitmentResult(
    Long recruitmentId,
    String companyName,
    String country,
    String region,
    String position,
    int reward,
    String skill
) {}
