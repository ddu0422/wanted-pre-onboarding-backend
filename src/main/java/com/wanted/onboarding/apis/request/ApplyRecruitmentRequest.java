package com.wanted.onboarding.apis.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApplyRecruitmentRequest(
    @JsonProperty(value = "member_id") Long memberId,
    @JsonProperty(value = "recruitment_id") Long recruitmentId
) {}
