package com.wanted.onboarding.apis.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FindAllRecruitmentResponse(
    List<RecruitmentResponse> recruitmentResponses
) {}
