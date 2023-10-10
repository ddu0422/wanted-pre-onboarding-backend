package com.wanted.onboarding.usecase.query;

import lombok.Builder;

@Builder
public record SearchRecruitmentQuery(
    String keyword
) {}
