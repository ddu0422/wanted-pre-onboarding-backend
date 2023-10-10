package com.wanted.onboarding.usecase.result;

import lombok.Builder;

import java.util.List;

@Builder
public record GetRecruitmentResult(
    Long recruitmentId,
    String companyName,
    String country,
    String region,
    String position,
    int reward,
    String skill,
    String description,
    List<Long> otherRecruitmentIds
) {}
