package com.wanted.onboarding.apis.response;

import lombok.Builder;

@Builder
public record RecruitmentResponse(
    Long recruitmentId, String companyName, String country, String region, String position, int reward, String skill
) {}
