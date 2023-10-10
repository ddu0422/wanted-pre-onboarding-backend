package com.wanted.onboarding.apis.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetRecruitmentResponse(
    Long recruitmentId, String companyName, String country, String region, String position,
    int reward, String skill, String description, List<Long> otherRecruitmentIds
) {}
