package com.wanted.onboarding.usecase.query;

import lombok.Builder;

@Builder
public record GetRecruitmentQuery(
    Long recruitmentId
) {}
